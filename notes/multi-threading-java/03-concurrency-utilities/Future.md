# Future in Java
## Introduction
The `Future` interface in Java is a key component of the concurrency API and represents the result of an asynchronous computation. It provides methods to check if the computation is complete, retrieve the result, and manage the execution of the task.

## Usage of Future
`Future` is commonly used with the `ExecutorService` framework to execute tasks in separate threads and retrieve their results once they are completed.

Here's a basic example that submits callable task to executor service:

```java
ExecutorService executor = Executors.newSingleThreadExecutor();
Callable<String> task = () -> {
    // Simulate some computation
    Thread.sleep(2000);
    return "Result of the task";
};

Future<String> future = executor.submit(task);
```

## Methods of Future
The Future interface provides several methods for managing the lifecycle of the asynchronous task:

- `isDone()`: Returns true if the task is completed, canceled, or failed.

- `get()`: Retrieves the result of the computation. This method blocks until the result is available or the task is canceled/failed.

- `get(long timeout, TimeUnit unit)`: Retrieves the result, but waits at most the specified timeout. Throws TimeoutException if the timeout expires before the result is available.

- `cancel(boolean mayInterruptIfRunning)`: Attempts to cancel the execution of the task. The mayInterruptIfRunning parameter specifies whether the thread executing the task should be interrupted.

- `isCancelled()`: Returns true if the task was canceled before it completed.

## Handling Exceptions

When using the `get()` method, it's important to handle possible exceptions:

- **InterruptedException:** Thrown if the current thread was interrupted while waiting for the result.
- **ExecutionException:** Thrown if the task threw an exception during execution. The actual exception can be retrieved using getCause().

#### Example with Exception Handling

Here's an example that demonstrates handling exceptions when retrieving the result:

```java
try {
    String result = future.get();
    System.out.println(result);
} catch (InterruptedException e) {
    // Handle interruption
} catch (ExecutionException e) {
    // Handle execution exception
    Throwable cause = e.getCause();
    // Process the cause
}
```