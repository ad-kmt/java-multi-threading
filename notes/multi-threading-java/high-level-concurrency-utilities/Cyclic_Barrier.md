# CyclicBarrier in Java
## Introduction
`CyclicBarrier` is a synchronization aid in Java's` java.util.concurrent `package that allows a set of threads to all wait for each other to reach a common barrier point.

It is useful in scenarios where multiple threads need to perform different parts of a computation and must wait for all other threads to complete their parts before proceeding to the next phase.

## How CyclicBarrier Works
- When creating a `CyclicBarrier`, you specify the number of threads (parties) that must reach the barrier before any of them can proceed.
- Each thread calls the `await()` method on the `CyclicBarrier` when it reaches the barrier point.
- The `await()` method blocks until all other threads have also called `await()`.
- Once all threads have reached the barrier, the barrier is tripped, and all waiting threads are released to continue their work.
- The `CyclicBarrier` can be reused after all waiting threads are released, making it cyclic.

## Example Usage

```java
import java.util.concurrent.*;

class Worker implements Runnable {
    private CyclicBarrier barrier;

    public Worker(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting at the barrier");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numThreads);

        for (int i = 0; i < numThreads; i++) {
            new Thread(new Worker(barrier), "Thread " + (i + 1)).start();
        }
    }
}
```
In this example, three threads are created, each executing a `Worker` runnable that waits at a `CyclicBarrier`. Once all three threads have reached the barrier, they are all released to continue.

## Key Methods
**await():** Used by a thread to wait at the barrier. Blocks until all parties have arrived at the barrier.
**getParties():** Returns the number of parties required to trip the barrier.
**getNumberWaiting():** Returns the number of parties currently waiting at the barrier.
**reset():** Resets the barrier to its initial state.

## Use Cases
**CyclicBarrier** is commonly used in parallel algorithms where a computation is divided into multiple steps, and all threads need to synchronize at the end of each step before proceeding to the next step.