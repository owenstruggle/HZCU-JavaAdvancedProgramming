package com.owem.answer02;

import java.util.*;

/**
 * @author Owem
 * @date 2022/11/13 16:15
 * @description TODO
 **/
public class CountNumberMaxFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        while (true) {
            int i = Integer.parseInt(scanner.next());
            if (i == 0) {
                break;
            } else {
                numbers.add(i);
            }
        }

        Map<Integer, Integer> numbersHashMap = new HashMap<>();

        for (Integer i : numbers) {
            int v = 1;
            if (numbersHashMap.containsKey(i)) {
                v += numbersHashMap.get(i);
            }
            numbersHashMap.put(i, v);
        }

        int maxNumber = (int)getMaxValue(numbersHashMap);
        List<Integer> outputList = new ArrayList<>();
        Set<Integer> keySet = numbersHashMap.keySet();
        for (Integer key : keySet) {
            if (maxNumber == numbersHashMap.get(key)) {
                outputList.add(key);
            }
        }
        outputList.sort(Comparator.comparingInt(o -> o));
        for (Integer key : outputList) {
            System.out.println(key);
        }
    }

    public static Object getMaxValue(Map<Integer, Integer> map) {
        if (map == null)
            return null;
        int length =map.size();
        Collection<Integer> c = map.values();
        Object[] obj = c.toArray();
        Arrays.sort(obj);
        return obj[length-1];
    }
}
