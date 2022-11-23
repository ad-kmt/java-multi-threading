package org.kmt.multithreading.threadCreation;

public class ThreadCreation2 {

    public static void main(String[] args) {
        NewThread newThread = new NewThread();
        newThread.start();
    }
}
class NewThread extends Thread{
    @Override
    public void run() {
        System.out.println("Hello from " + this.getName());
    }
}
