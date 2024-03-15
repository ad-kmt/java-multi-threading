# Inter-Thread Communication in Java
## Introduction
Inter-thread communication in Java allows multiple threads to communicate and coordinate their actions, ensuring proper synchronization and avoiding issues like deadlocks and race conditions.

## The wait(), notify(), and notifyAll() Methods
Java provides three methods for inter-thread communication, which must be used within a synchronized context:

### wait()
Causes the current thread to wait indefinitely until another thread calls notify() or notifyAll() on the same object.
The thread releases the lock it holds on the object and enters the waiting state.
The thread can be interrupted and will throw an InterruptedException.

```java
synchronized (object) {
    while (condition) {
        object.wait();
    }
}
```
### notify()
Wakes up a single thread that is waiting on the object's monitor.
If multiple threads are waiting, one of them is chosen to be awakened. The choice is arbitrary and depends on the JVM implementation.
The notified thread will compete for the lock once it is released by the notifying thread.

```java
synchronized (object) {
    // Update condition
    object.notify();
}
```

### notifyAll()
Wakes up all threads that are waiting on the object's monitor.
All awakened threads will compete for the lock, but only one will acquire it and proceed.

```java
synchronized (object) {
    // Update condition
    object.notifyAll();
}
```

## Example: Producer-Consumer Problem
A classic example of inter-thread communication is the producer-consumer problem, where a producer thread produces data and a consumer thread consumes it.

```java
class SharedBuffer {
    private int data;
    private boolean isEmpty = true;

    public synchronized void produce(int newData) throws InterruptedException {
        while (!isEmpty) {
            wait();
        }
        data = newData;
        isEmpty = false;
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (isEmpty) {
            wait();
        }
        isEmpty = true;
        notifyAll();
        return data;
    }
}
```

## Best Practices
- Always call `wait()` , `notify()` , and `notifyAll()` within a synchronized block or method to ensure proper synchronization.

- Use `wait()` in a loop with a condition check to avoid spurious wakeups.

- Prefer `notifyAll()` over `notify()` when multiple threads may be waiting for the same condition to avoid missed signals.