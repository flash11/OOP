package org.example;

import java.lang.Thread;

import static org.example.PrimeNumber.generatePrimes;



public class ThreadsPrimes {
    private static final int NUM_THREADS = 4;

    public static void main(String[] args) {
        int[] primes = generatePrimes(30, 15); // Пример массива с простыми числами и одним непростым числом

        Thread[] threads = new Thread[NUM_THREADS];
        ThreadRun[] tasks = new ThreadRun[NUM_THREADS];

        int chunkSize = primes.length / NUM_THREADS;
        int startIndex = 0;
        int endIndex = chunkSize;

        // Создаем и запускаем потоки для каждого участка массива
        for (int i = 0; i < NUM_THREADS; i++) {
            if (i == NUM_THREADS - 1) {
                endIndex = primes.length; // Последний поток получает остаток
            }
            tasks[i] = new ThreadRun(primes, startIndex, endIndex);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
            startIndex = endIndex;
            endIndex = Math.min(endIndex + chunkSize, primes.length);
        }

        // Ждем завершения всех потоков
        for (int i = 0; i < NUM_THREADS; i++) {
            try {
                threads[i].join();
                if (!tasks[i].getResult()) {
                    System.out.println("Найдено непростое число в массиве.");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

