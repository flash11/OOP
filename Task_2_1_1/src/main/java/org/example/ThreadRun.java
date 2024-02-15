package org.example;

class ThreadRun implements Runnable {
    private final int[] primes;
    private final int startIndex;
    private final int endIndex;
    private boolean result;

    public ThreadRun(int[] primes, int startIndex, int endIndex) {
        this.primes = primes;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            if (PrimeNumber.findNonePrime(primes)) {
                result = false;
                return;
            }
        }
        result = true;
    }

    public boolean getResult() {
        return result;
    }


}

