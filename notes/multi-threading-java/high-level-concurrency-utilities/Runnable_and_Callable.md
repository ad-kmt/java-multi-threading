# Runnable and Callable in Java
## Introduction
In Java, `Runnable` and `Callable` are two interfaces commonly used for defining tasks that can be executed by threads or thread pools. They provide a way to encapsulate the logic of a task in an object that can be run concurrently.

## Runnable Interface
The `Runnable` interface is a functional interface that has a single method, `run()`, which takes no arguments and returns no value. It is used to define a task that can be executed by a thread.

##### Example of Runnable:
```java
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        // Task logic goes here
        System.out.println("Running in a separate thread!");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
```

## Callable Interface
The `Callable` interface is similar to `Runnable`, but it has some key differences:
- It has a method called `call()` instead of `run()`.
- The `call()` method can return a result.
- The `call()` method can throw checked exceptions.

Callable is often used when you need to execute a task that returns a result or when you need to handle exceptions in the task.

##### Example of Callable:
```java
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        // Task logic goes here
        return "Result from Callable";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new MyCallable());

        // Wait for the result of the Callable
        String result = future.get();
        System.out.println(result);

        executor.shutdown();
    }
}
```

## Differences between Runnable and Callable
- **Return Value:** Runnable cannot return a result, while Callable can return a result of a specific type.
- **Exception Handling:** Runnable cannot throw checked exceptions, while Callable can throw checked exceptions.
- **Usage with ExecutorService:** When submitted to an ExecutorService, a Runnable task does not return a value, while a Callable task returns a Future object that can be used to retrieve the result of the task.