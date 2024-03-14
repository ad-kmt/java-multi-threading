package org.kmt.multithreading.concepts.thread.daemon;

/**
 * Demonstration of Daemon Threads in Java.
 *
 * In this program, we create a daemon thread that runs an infinite loop, printing a message every second.
 * The thread is set as a daemon thread by calling setDaemon(true) before starting the thread.
 *
 * The main thread sleeps for 5 seconds and then exits. When the main thread (or all non-daemon threads) exits,
 * the JVM will terminate the daemon threads, even though they are still running. This behavior is demonstrated
 * by the fact that the daemon thread stops running when the main thread exits.
 */
public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("Daemon thread is running...");
                        Thread.sleep(1000); // Sleep for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Set the thread as a daemon thread
        daemonThread.setDaemon(true);

        // Start the daemon thread
        daemonThread.start();

        // Main thread sleeps for 5 seconds, then exits
        try {
            System.out.println("Main thread is sleeping...");
            Thread.sleep(5000);
            System.out.println("Main thread is exiting.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
