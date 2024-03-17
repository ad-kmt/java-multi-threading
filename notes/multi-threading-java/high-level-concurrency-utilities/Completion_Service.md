# CompletionService in Java
## Introduction
The `CompletionService` interface in Java's `java.util.concurrent` package is a utility that simplifies the management of a set of asynchronous tasks. It decouples the production of asynchronous tasks from the consumption of their results, allowing you to process completed tasks in the order they finish.

## Overview
A `CompletionService` manages a queue of `Future` objects representing the results of submitted tasks. When a task completes, its corresponding Future is placed in the completion queue, allowing you to retrieve completed tasks in the order they finish, regardless of their submission order.

## Usage
#### Creating a CompletionService
A `CompletionService` is typically constructed with an `Executor` or `ExecutorService` that executes the tasks:

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
```

#### Submitting Tasks
Tasks can be submitted to the CompletionService using the `submit()` method, which takes a `Callable` or `Runnable` task:

```java
completionService.submit(new MyCallableTask());
```

#### Retrieving Completed Tasks
You can retrieve the results of completed tasks using the `take()` or `poll()` methods:

```java
try {
    Future<Integer> completedFuture = completionService.take();
    Integer result = completedFuture.get();
    // Process the result
} catch (InterruptedException | ExecutionException e) {
    // Handle exceptions
}
```

The `take()` method blocks until a completed task is available, while `poll()` can be used for non-blocking retrieval.

## Example
Here's a simple example that demonstrates the use of a CompletionService:

```java
import java.util.concurrent.*;

public class CompletionServiceExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        // Submit tasks to the completion service
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            completionService.submit(() -> {
                Thread.sleep(finalI * 1000); // Simulate variable execution time
                return "Task " + finalI + " completed";
            });
        }

        // Retrieve and process the results as they become available
        for (int i = 0; i < 5; i++) {
            Future<String> completedFuture = completionService.take();
            String result = completedFuture.get();
            System.out.println(result);
        }

        executor.shutdown();
    }
}
```
In this example, five tasks are submitted to the CompletionService, each with a simulated variable execution time. The main thread retrieves and processes the results in the order they complete.