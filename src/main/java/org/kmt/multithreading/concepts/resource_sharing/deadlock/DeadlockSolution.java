package org.kmt.multithreading.concepts.resource_sharing.deadlock;

import java.util.Random;

public class DeadlockSolution {

    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        Thread threadA = new Thread(new TrainA(intersection));
        Thread threadB = new Thread(new TrainB(intersection));

        threadA.start();
        threadB.start();
    }


    public static class TrainA implements Runnable{
        private Intersection intersection;
        private Random random = new Random();

        TrainA(Intersection intersection){
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(random.nextInt(5));
                    intersection.takeRoadA();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static class TrainB implements Runnable{
        private Intersection intersection;
        private Random random = new Random();

        TrainB(Intersection intersection){
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(random.nextInt(5));
                    intersection.takeRoadB();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static class Intersection {
        private Object roadA = new Object();
        private Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA){
                System.out.println("Road A is locked by thread " + Thread.currentThread().getName());

                synchronized (roadB){
                    System.out.println("Train is passing through road A");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        public void takeRoadB() {
            synchronized (roadA){
                System.out.println("Road B is locked by thread " + Thread.currentThread().getName());

                synchronized (roadB){
                    System.out.println("Train is passing through road B");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

}
