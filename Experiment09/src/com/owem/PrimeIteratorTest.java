package com.owem;

/**
 * @author Owem
 * @date 2022/11/15 16:58
 * @description TODO
 **/
public class PrimeIteratorTest {
    public static void main(String[] args) {
        PrimeIterator primeIterator = new PrimeIterator(8000);
        for (Integer i : primeIterator.getPrimeNumberArray()) {
            System.out.println(i);
        }
        System.out.println("共 " + primeIterator.getPrimeNumberArray().size() + " 个素数");
    }
}
