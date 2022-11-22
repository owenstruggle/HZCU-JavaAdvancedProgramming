package com.owem;

import java.util.ArrayList;

/**
 * @author Owem
 * @date 2022/11/15 16:58
 * @description TODO
 **/
public class PrimeIterator {
    private int maxNumber;
    private ArrayList<Integer> primeNumberArray = new ArrayList<>();

    public PrimeIterator() {
    }

    public PrimeIterator(int maxNumber) {
        this.maxNumber = maxNumber;
        getPrimeNumber();
    }

    public ArrayList<Integer> getPrimeNumberArray() {
        return primeNumberArray;
    }

    private void getPrimeNumber() {
        for (int i = 0; i < maxNumber; i++) {
            if (isPrime(i)) {
                primeNumberArray.add(i);
            }
        }
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
