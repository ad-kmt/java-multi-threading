package org.kmt.multithreading.threadCoordination;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadJoiningRaceCondition {

    public static void main(String[] args) {
        List<Long> inputNumbers = Arrays.asList(100000000L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);

        List<FactorialThread> threads = new ArrayList<>();

        for(Long inputNumber : inputNumbers){
            threads.add(new FactorialThread(inputNumber));
        }

        for (FactorialThread thread : threads){
            thread.start();
        }

        for (FactorialThread factorialThread: threads){

            // RACE CONDITION
            if (factorialThread.isFinished){
                System.out.println("Factorial for number " + factorialThread.getInputNumber() + " is: " + factorialThread.getResult());
            } else {
                System.out.println("Calculation for number " + factorialThread.getInputNumber() + " is still in progress.");
            }
        }

    }

    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        FactorialThread(long inputNumber){
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(this.inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(Long n){
            BigInteger tempResult = BigInteger.ONE;
            for (long i = 1; i < n; i++) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public long getInputNumber() {
            return inputNumber;
        }
    }
}
