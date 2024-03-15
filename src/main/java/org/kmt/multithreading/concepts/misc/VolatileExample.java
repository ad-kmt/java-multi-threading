package org.kmt.multithreading.concepts.misc;

/**
 * This program demonstrates the use of the 'volatile' keyword in Java for thread synchronization.
 * We have a SharedObject class with a volatile integer variable 'counter'.
 * The 'incrementCounter' method increments this counter, while the 'getCounter' method returns its current value.
 *
 * Two threads are created:
 *  1. 'incrementThread': This thread increments the counter 1000 times using the 'incrementCounter' method.
 *  2. 'readThread': This thread reads the current value of the counter using the 'getCounter' method and prints it.
 *
 * Because the 'counter' variable is declared as volatile, changes made to it in one thread are immediately visible to other threads.
 * This ensures that both threads always see the most recent value of the counter, providing consistent visibility across threads.
 *
 * The main thread waits for both 'incrementThread' and 'readThread' to finish execution using the 'join' method.
 * Finally, the main thread prints the final value of the counter, which should reflect the updates made by the 'incrementThread'.
*/
public class VolatileExample {
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();

        // Create a thread to increment the counter
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedObject.incrementCounter();
            }
        });

        // Create a thread to read the counter value
        Thread readThread = new Thread(() -> {
            int value = sharedObject.getCounter();
            System.out.println("Counter value: " + value);
        });

        // Start both threads
        incrementThread.start();
        readThread.start();

        // Wait for both threads to finish
        try {
            incrementThread.join();
            readThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final counter value
        System.out.println("Final counter value: " + sharedObject.getCounter());
    }


    static class SharedObject {
        // Declare a volatile variable
        private volatile int counter = 0;

        // Method to increment the counter
        public void incrementCounter() {
            counter++;
        }

        // Method to get the current value of the counter
        public int getCounter() {
            return counter;
        }
    }
}
