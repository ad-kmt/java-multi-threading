package org.kmt.multithreading.concepts.thread.state;


/**
 * The ThreadState class demonstrates the various states that a thread
 * can be in during its lifecycle in Java. It uses two threads, t1 and t2,
 * to illustrate the transition between states such as NEW, RUNNABLE, TIMED_WAITING,
 * and TERMINATED.
 *
 * At the beginning (0ms):
 * - t1 is in the NEW state.
 *
 * After t1.start() is called (approximately 0ms):
 * - t1 moves to the RUNNABLE state.
 *
 * After t2.start() is called inside the run() method of t1 (approximately 0ms):
 * - t2 is in the NEW state before start() is called.
 * - t2 moves to the RUNNABLE state after start() is called.
 *
 * After t1.sleep() and t2.sleep() is called for both threads in their run() methods (approximately 0ms) (order can be different)
 * - t1 is in TIMED_WAITING state
 * - t2 is in TIMED_WAITING state
 *
 * At approximately 100ms:
 * - t2 completes its first sleep of 100ms and exits the TIMED_WAITING state.
 * - t1 may still be in the TIMED_WAITING state due to its 200ms sleep.
 *
 * At approximately 200ms:
 * - t1 completes its 200ms sleep and exits the TIMED_WAITING state.
 * - If t1 immediately calls t2.join(), it enters the WAITING state until t2 completes.
 * - t2 might be in the RUNNABLE or TIMED_WAITING state depending on its execution progress.
 *
 * At approximately 300ms (after t2's second sleep ends):
 * - t2 completes its execution and moves to the TERMINATED state.
 * - t1, which was waiting for t2 to complete, now resumes execution and eventually moves to the TERMINATED state.
 *
 * The output of the program demonstrates these state transitions, highlighting the behavior of threads as they move through different states in their lifecycle.
 */

// ThreadState class implements the interface Runnable
public class ThreadState{
    public static Thread t1;
    public static Thread t2;

    // main method
    public static void main(String argvs[]) {

        // creating an object of the class ThreadState
        t1 = new Thread1();

        // thread t1 is spawned
        // The thread t1 is currently in the NEW state.
        System.out.println("The state of thread t1 after spawning it - " + t1.getState());

        // invoking the start() method on
        // the thread t1
        t1.start();

        // thread t1 is moved to the Runnable state
        System.out.println("The state of thread t1 after invoking the method start() on it - " + t1.getState());

        try {
            t1.join();
            System.out.println("The state of thread t1 when it has completed it's execution - " + t1.getState());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    // Thread1 class implements the interface Runnable
    static class Thread1 extends Thread {
        public void run() {
            t2 = new Thread2();

            // thread t2 is created and is currently in the NEW state.
            System.out.println("The state of thread t2 after spawning it - " + t2.getState());
            t2.start();

            // thread t2 is moved to the runnable state
            System.out.println("the state of thread t2 after calling the method start() on it - " + t2.getState());

            // try-catch block for the smooth flow of the  program
            try {
                // moving the thread t1 to the state timed waiting
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            System.out.println("The state of thread t2 after invoking the method sleep() on it - " + t2.getState());

            // try-catch block for the smooth flow of the  program
            try {
                // waiting for thread t2 to complete its execution
                t2.join();
                System.out.println("The state of thread t2 when it has completed it's execution - " + t2.getState());
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

        }
    }

    // Thread2 class implements the interface Runnable
    static class Thread2 extends Thread {
        public void run() {
            try {
                // moving thread t2 to the state timed waiting
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.out.println("The state of thread t1 while it invoked the method join() on thread t2 -" + ThreadState.t1.getState());

            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}


