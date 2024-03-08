# Basic Concepts of Multithreading in Java

## Introduction
Multithreading is a powerful feature in Java that allows you to run multiple threads (mini-programs) concurrently within a single program. Each thread operates independently and can perform different tasks simultaneously, making your program more efficient and responsive.

## Creating Threads
In Java, there are two main ways to create a thread:

### 1. Extending the Thread Class:

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
### 2. Implementing the Runnable Interface:

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