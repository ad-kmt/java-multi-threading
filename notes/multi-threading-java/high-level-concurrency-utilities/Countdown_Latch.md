# CountDownLatch in Java
## Introduction

A `CountDownLatch` is a tool in Java that lets one or more threads pause and wait for other threads to finish their tasks. You set it up with a number that represents how many tasks need to be done. As each task is completed, the number goes down. When the number reaches zero, the waiting threads can continue.

**Typical definition**: CountDownLatch is a synchronization utility class in the `java.util.concurrent` package that allows one or more threads to wait until a set of operations being performed by other threads is completed. It is initialized with a count, which represents the number of events that must occur before the latch is released.

## How It Works
##### Initialization
A CountDownLatch is created with a given count.

```java
CountDownLatch latch = new CountDownLatch(count);
```

##### Await
Threads that need to wait for the event call the` await()` method. They will be blocked until the count reaches zero.

```java
latch.await();

```
##### Count Down
Threads that are responsible for the event call the `countDown()` method each time the event occurs. This decrements the count.

```java
latch.countDown();

```
##### Release
When the count reaches zero, all waiting threads are released and can proceed.

## Example Usage
Consider a scenario where we have a main thread that needs to wait for three other threads to complete their tasks before it can proceed.

```java
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        // Create and start three worker threads
        for (int i = 1; i <= 3; i++) {
            Thread workerThread = new Thread(new Worker(i, latch));
			workerThread.start();
        }

        // Main thread waits for the latch to count down to zero
        latch.await();
        System.out.println("All workers have finished. Main thread can proceed.");
    }

    static class Worker implements Runnable {
        private final int id;
        private final CountDownLatch latch;

        Worker(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // Simulate some work
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println("Worker " + id + " has finished");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Decrement the latch count
                latch.countDown();
            }
        }
    }
}
```
In this example, the main thread creates a `CountDownLatch` with a count of 3 and starts three worker threads. Each worker thread simulates some work and then calls `countDown()` on the latch. The main thread waits for the latch to reach zero using `await() `and then proceeds once all workers have finished.

## Use Cases
- Waiting for resources to be initialized before starting an application.
- Synchronizing the start of multiple threads in a test or benchmark.
- Waiting for a group of tasks to complete before aggregating the results.