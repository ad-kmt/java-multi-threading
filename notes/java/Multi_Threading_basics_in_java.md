# Multithreading in Java

## Introduction
Multithreading is a powerful feature in Java that allows you to run multiple threads (mini-programs) concurrently within a single program. Each thread operates independently and can perform different tasks simultaneously, making your program more efficient and responsive.

## Creating Threads
In Java, there are two main ways to create a thread:

#### 1. Extending the Thread Class:

- Create a new class that extends the Thread class.
- Override the run() method to define the task the thread will perform.
- Create an instance of your class and call the start() method to begin execution.

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running.");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
    }
}
```
#### 2. Implementing the Runnable Interface:

- Create a new class that implements the Runnable interface.
- Implement the run() method to specify the task.
- Create an instance of Thread by passing an instance of your class to its constructor, then call the start() method.

```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread is running.");
    }
}

public class Main {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable);
        t1.start();
    }
}
```
## Thread Lifecycle and States
A thread in Java can be in one of the following states:

**New**: The thread is created but not yet started.
**Runnable**: The thread is ready to run and waiting for CPU time.
**Running**: The thread is executing its run() method.
**Blocked**: The thread is waiting for a monitor lock to enter a synchronized block/method. (The thread is waiting to access a section of code that is currently being used by another thread.)
**Waiting**: The thread is waiting indefinitely for another thread to perform a specific action.
**Timed Waiting**: The thread is waiting for a specified amount of time.
**Terminated**: The thread has completed its execution and exited.

## Thread Priority
Each thread has a priority that helps the scheduler decide when to switch between threads. In Java, thread priorities range from MIN_PRIORITY (1) to MAX_PRIORITY (10), with NORM_PRIORITY (5) being the default.

You can set a thread's priority using the `setPriority()` method and get its priority using the `getPriority()` method.

## Thread Methods
Some important methods in the Thread class are:

`thread.start()`: Starts the thread execution.
`thread.run()`: Defines the code to be executed by the thread.
`thread.sleep(long millis)`: Makes the current thread sleep for the specified milliseconds.
`thread.join()`: Waits for the thread to finish.
`thread.interrupt()`: Interrupts the thread.