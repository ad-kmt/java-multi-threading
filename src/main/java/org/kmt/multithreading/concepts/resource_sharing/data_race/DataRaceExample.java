package org.kmt.multithreading.concepts.resource_sharing.data_race;

public class DataRaceExample {

    public static void main(String[] args) {
        SharedClass shared = new SharedClass();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                shared.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                shared.checkForDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }

    public static class SharedClass{
        private int x = 0;
        private int y = 0;

        public void increment(){
            x++;
            y++;
        }

        public void checkForDataRace(){
            if(y>x){
                System.out.println("Y > X - Data race detected");
            }
        }
    }

}
