# Thread Pooling in Java

## Introduction

Thread pooling is a technique used to manage multiple threads in a multithreaded application. It involves creating a pool of worker threads that can execute tasks concurrently. Thread pooling helps to improve the performance and efficiency of the application by reusing existing threads instead of creating new ones for each task.

### Why Use Thread Pools?

- **Resource Management:** Creating and destroying threads for each task can be resource-intensive. Thread pools reuse existing threads, reducing the overhead of thread creation and destruction.
- **Performance:** By limiting the number of active threads, thread pools can prevent excessive CPU usage and improve overall application performance.
- **Task Management:** Thread pools provide a way to manage and queue tasks, ensuring that they are executed in an organized manner.

## Executor Framework in Java
The Executor Framework is a set of interfaces and classes in the` java.util.concurrent` package that provides a flexible and scalable way to manage thread pools and execute tasks concurrently.

### Key Components

#### Executor Interface
- The root interface for executing tasks asynchronously.
- Defines a single execute ( `Runnable` command) method for executing a given command in the future.

#### ExecutorService Interface
- An extension of the Executor interface with additional methods for lifecycle management and task submission.
- Provides methods for submitting `Callable` and `Runnable` tasks, shutting down the executor, and tracking the progress of tasks.

#### ScheduledExecutorService Interface
- An extension of ExecutorService for scheduling tasks to run after a given delay or at fixed intervals.
- Useful for recurring tasks or tasks that need to be executed after a certain amount of time.

**Note**: You can use the Executor framework in Java to create your own implementation of thread pools or use Third Party Implementations. e.g. Apache Commons Lang, Guava, Hystrix (Netflix) etc.

## Thread Pools provided by Java
Java provides several types of thread pools through the Executors class:

#### FixedThreadPool
- A thread pool with a fixed number of threads.
- If all threads are busy and new tasks are submitted, they are queued until a thread becomes available.

```java
ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
```

#### CachedThreadPool
- A thread pool that creates new threads as needed and reuses previously constructed threads when available.
- Idle threads are terminated after a certain period of inactivity.
- Suitable for applications with a varying number of short-lived tasks.

```java
ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
```

#### ScheduledThreadPool
- A thread pool for executing scheduled or periodic tasks.

```java
ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
```

## Submitting Tasks
- Tasks can be submitted as `Runnable` or `Callable` objects using methods like `execute` , `submit` , `schedule` , `scheduleAtFixedRate` , and `scheduleWithFixedDelay` .
- The `submit` method returns a Future object that can be used to retrieve the result of a Callable task or to check the status of the task.

## Shutting Down Executor Service
- It's important to shut down the executor service properly to release system resources and allow the application to terminate gracefully.
- Methods like `shutdown` and `shutdownNow` can be used to initiate the shutdown process.

## Example: Creating a Fixed Sized Thread Pool in Java

Here's how you can create a simple fixed-size thread pool using the Executors class:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed-size thread pool with 4 worker threads
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Submit tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            int taskId = i;
            executorService.submit(() -> {
                System.out.println("Executing task " + taskId + " in thread " + Thread.currentThread().getName());
            });
        }

        // Shut down the thread pool
        executorService.shutdown();
    }
}
```

**Output:**

    Executing task 0 in thread pool-1-thread-1
    Executing task 1 in thread pool-1-thread-2
    Executing task 2 in thread pool-1-thread-3
    Executing task 3 in thread pool-1-thread-4
    Executing task 4 in thread pool-1-thread-1
    Executing task 5 in thread pool-1-thread-2
    Executing task 6 in thread pool-1-thread-3
    Executing task 7 in thread pool-1-thread-4
    Executing task 8 in thread pool-1-thread-1
    Executing task 9 in thread pool-1-thread-2

**Note**: The actual output may vary slightly in terms of the order of execution and the thread names, as it depends on the scheduling and execution by the thread pool.