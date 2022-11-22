package com.owem.answer01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Owem
 * @date 2022/11/1 15:33
 * @description TODO
 **/
public class CountWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sentences = scanner.nextLine();
        HashMap<String, Integer> wordsHashMap = new HashMap<>();

        for (String s : sentences.split("[,.; !]")) {
            int v = 1;
            if (wordsHashMap.containsKey(s)) {
                v += wordsHashMap.get(s);
            }
            wordsHashMap.put(s, v);
        }

        wordsHashMap.remove(" ");
        wordsHashMap.remove("");

        System.out.println(wordsHashMap);
    }
}
