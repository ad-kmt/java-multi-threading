package org.kmt.multithreading.concepts.thread.daemon;

public class DaemonThreadExample2 extends Thread{

    public static void main(String[] args){
        MyThread t1 = new MyThread();//creating thread
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();

        t1.setDaemon(true);//now t1 is daemon thread

        t1.start();//starting threads
        t2.start();
        t3.start();
    }

    public static class MyThread extends Thread{
        public void run(){
            if(Thread.currentThread().isDaemon()){//checking for daemon thread
                System.out.println("daemon thread work");
            }
            else{
                System.out.println("user thread work");
            }
        }
    }
}
