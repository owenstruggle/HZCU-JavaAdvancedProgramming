package com.owem.answer01;

import java.util.*;

/**
 * @author Owem
 * @date 2022/11/13 16:13
 * @description TODO
 **/
public class CountWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sentences = new StringBuilder();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("****")) {
                break;
            } else {
                sentences.append(s);
            }
        }

        Map<String, Integer> wordsHashMap = new HashMap<>();

        for (String s : sentences.toString().split("[,.; !]")) {
            int v = 1;
            if (wordsHashMap.containsKey(s)) {
                v += wordsHashMap.get(s);
            }
            wordsHashMap.put(s, v);
        }

        wordsHashMap.remove(" ");
        wordsHashMap.remove("");

        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordsHashMap.entrySet());
        list.sort(Map.Entry.comparingByKey());
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey());
        }
    }
}
