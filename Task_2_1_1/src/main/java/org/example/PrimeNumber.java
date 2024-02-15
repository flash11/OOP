package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// нужно определить есть ли хотя бы одно непростое

public class PrimeNumber {

    public static void main(String[] args) {
        System.out.println(isPrime(8));
        System.out.println(generatePrimes(100, 12));
        int[] array = generatePrimes(100, 12);
        for (int i : array) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) Math.floor(Math.sqrt(number))).noneMatch(i -> number % i == 0);
    }

    public static int[] generatePrimes(int upperLimit, int nonePrime) {
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= upperLimit; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        primes.add(nonePrime);
        return primes.stream().mapToInt(i -> i).toArray();
    }


    public static boolean findNonePrime(int[] nums) {
        for (int num : nums) {
            if (isPrime(num)) {
                return false;
            }
        }
        return true;
    }
}