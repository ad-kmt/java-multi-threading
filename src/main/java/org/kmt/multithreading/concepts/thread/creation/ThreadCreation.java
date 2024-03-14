package org.kmt.multithreading.concepts.thread.creation;

/**
 * This class demonstrates different ways to create and run threads in Java.
 *
 * Method 1: Extending the Thread class
 * - Create a class that extends the Thread class and override the run() method.
 * - Instantiate the class and call the start() method to run the thread.
 *
 * Method 2: Implementing the Runnable interface
 * - Create a class that implements the Runnable interface and implement the run() method.
 * - Pass an instance of this class to a Thread object and call the start() method.
 *
 * Method 3: Using an anonymous inner class
 * - Create an anonymous inner class that implements Runnable and pass it to a Thread object.
 * - Call the start() method to run the thread.
 *
 * Method 4: Using a lambda expression (Java 8+)
 * - Use a lambda expression to define the run() method of the Runnable interface.
 * - Pass the lambda expression to a Thread object and call the start() method.
 *
 * Method 5: Using the Thread constructor with a subclass of Runnable
 * - Create a class that extends Thread (which indirectly implements Runnable) and override the run() method.
 * - Instantiate the class and call the start() method to run the thread.
 *
 * Each method prints the thread name within the run() method using Thread.currentThread().getName() or this.getName().
 */
public class ThreadCreation {

    public static void main(String[] args) {
        // Method 1: Extending the Thread class
        Thread thread1 = new ThreadExample();
        thread1.start();

        // Method 2: Implementing the Runnable interface
        Runnable runnable = new RunnableExample();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        // Method 3: Using an anonymous inner class
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running thread using an anonymous inner class: " + Thread.currentThread().getName());
            }
        });
        thread3.start();

        // Method 4: Using a lambda expression (Java 8+)
        Thread thread4 = new Thread(() -> System.out.println("Running thread using a lambda expression: " + Thread.currentThread().getName()));
        thread4.start();

        // Method 5: Using the Thread constructor with a subclass of Runnable
        Thread thread5 = new Thread(new RunnableSubclass());
        thread5.start();
    }

    // Method 1: Extending the Thread class
    static class ThreadExample extends Thread {
        @Override
        public void run() {
            System.out.println("Running thread by extending Thread class: " + this.getName());
        }
    }

    // Method 2: Implementing the Runnable interface
    static class RunnableExample implements Runnable {
        @Override
        public void run() {
            System.out.println("Running thread by implementing Runnable interface: " + Thread.currentThread().getName());
        }
    }

    // Method 5: Using the Thread constructor with a subclass of Runnable
    static class RunnableSubclass extends Thread {
        @Override
        public void run() {
            System.out.println("Running thread using Thread constructor with Runnable subclass: " + this.getName());
        }
    }
}