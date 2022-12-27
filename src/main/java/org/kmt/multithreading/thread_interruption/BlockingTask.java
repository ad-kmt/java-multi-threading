package org.kmt.multithreading.thread_interruption;

public class BlockingTask {

    public static void main(String[] args) {

        NewThread newThread = new NewThread();
        newThread.start();

        newThread.interrupt();
    }

    public static class NewThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }
}
