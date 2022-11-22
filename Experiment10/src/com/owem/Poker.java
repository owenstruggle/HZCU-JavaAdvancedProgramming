package com.owem;

import java.util.Objects;

/**
 * @author Owem
 * @date 2022/11/22 17:08
 * @description TODO
 **/
public class Poker {
    public int color;  // 花色 1~4 代表 黑桃、红桃、梅花、方块
    public int point;  // 点数 2~16 代表 2、3、4、5、6、7、8、9、10、J、Q、K、A、小王、大王

    public Poker() {
    }

    public Poker(int color, int point) {
        this.color = color;
        this.point = point;
    }

    @Override
    public String toString() {
        String colorResult = switch (color) {
            case 1 -> "黑桃";
            case 2 -> "红桃";
            case 3 -> "梅花";
            case 4 -> "方块";
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };
        String pointResult = switch (point) {
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            case 14 -> "A";
            case 15 -> "小王";
            case 16 -> "大王";
            default -> String.valueOf(point);
        };

        if (Objects.equals(pointResult, "小王") || Objects.equals(pointResult, "大王")) {
            return pointResult;
        }
        return colorResult + " " + pointResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Poker poker = (Poker) o;

        if (color != poker.color) return false;
        return point == poker.point;
    }

    @Override
    public int hashCode() {
        int result = color;
        result = 31 * result + point;
        return result;
    }
}
