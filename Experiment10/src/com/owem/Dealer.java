package com.owem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Owem
 * @date 2022/11/22 16:51
 * @description TODO
 **/
public class Dealer {
    private static final ArrayList<Poker> library = new ArrayList<>();  // 牌库
    private static final HashMap<Integer, ArrayList<Poker>> gamerCard = new HashMap<>();  // 玩家手牌
    private static final ArrayList<Poker> bottomCard = new ArrayList<>();  // 底牌
    private static final ArrayList<Poker> standardBarrel = new ArrayList<>();  // 标准桶
    private static final AtomicBoolean isLock = new AtomicBoolean(false);  // 锁初始化变量

    private static void fillContainer() {
        // 在牌库里填充两幅标准套牌
        for (int time = 0; time < 2; time++) {
            for (int color = 1; color <= 4; color++) {
                for (int point = 2; point <= 14; point++) {
                    library.add(new Poker(color, point));
                }
            }
            library.add(new Poker(1, 16));  // 大王
            library.add(new Poker(1, 15));  // 小王
        }
        // 洗牌
        Collections.shuffle(library);
        // 初始化标准桶
        standardBarrel.add(new Poker(2, 5));  // 红桃 5
        standardBarrel.add(new Poker(1, 16));  // 大王
        standardBarrel.add(new Poker(1, 15));  // 小王
        standardBarrel.add(new Poker(1, 3));  // 黑桃 3
        standardBarrel.add(new Poker(3, 3));  // 梅花 3
        standardBarrel.add(new Poker(1, 2));  // 黑桃 2
        standardBarrel.add(new Poker(3, 2));  // 梅花 2
        standardBarrel.add(new Poker(2, 2));  // 红桃 2
        standardBarrel.add(new Poker(4, 2));  // 方块 2
        for (int color = 1; color <= 4; color++) {
            for (int point = 14; point > 2; point--) {
                if (color == 2 && point == 5) continue;
                if ((color == 1 || color == 3) && point == 3) continue;
                standardBarrel.add(new Poker(color, point));
            }
        }
    }

    // 发牌
    private static void deal() {
        for (int time = 0; time < 25; time++) {
            for (int gamerId = 0; gamerId < 4; gamerId++) {
                gamerCard.get(gamerId).add(library.get(4 * time + gamerId));
            }
        }
        for (int i = 100; i < library.size(); i++) {
            bottomCard.add(library.get(i));
        }
    }

    public static boolean lock(){    //加锁
        return isLock.compareAndSet(false, true);
    }

    public static void unlock() {   //解锁
        isLock.set(false);
    }

    // 桶排序
    private static void cardSort(ArrayList<Poker> pokerList) {
        while (!lock()) {}  //加锁失败，自旋
        System.out.println("----当前线程: " + Thread.currentThread().getName() + ", 手牌数: " + pokerList.size() + "----");
        for (Poker p1 : standardBarrel) {
            for (Poker p2 : pokerList) {
                if (p1.equals(p2)) System.out.println(p1);
            }
        }

        unlock();
    }

    private static void init() {
        fillContainer();
        // 创建玩家
        for (int gamerId = 0; gamerId < 4; gamerId++) {
            gamerCard.put(gamerId, new ArrayList<>());
        }
        deal();
    }

    public static void main(String[] args) {
        init();
        System.out.println("-----------------底牌数: " + bottomCard.size() + "-----------------");
        for (Poker p : bottomCard) {
            System.out.println(p);
        }
        ExecutorService service = Executors.newFixedThreadPool(4);   //线程池
        service.submit(() -> cardSort(gamerCard.get(0)));
        service.submit(() -> cardSort(gamerCard.get(1)));
        service.submit(() -> cardSort(gamerCard.get(2)));
        service.submit(() -> cardSort(gamerCard.get(3)));
    }
}
