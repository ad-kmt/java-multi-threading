# Locks in Java
## Introduction
Locks in Java are synchronization mechanisms that allow threads to have controlled access to shared resources. They are essential for preventing race conditions and ensuring thread safety in concurrent applications.

Here's how it works:

##### 1. Locking
When a thread wants to execute a critical section of code, it tries to "lock" the object that contains that code. This is like saying, "I'm using this resource right now, please wait your turn."
##### 2. Exclusive Access
Once a thread has locked the object, no other thread can lock the same object until the first thread is done. This ensures that only one thread can use the resource at a time.
##### 3. Unlocking
When the thread is finished with the critical section, it "unlocks" the object, allowing other threads to lock it and use the resource.

## Intrinsic Locks vs. Explicit Locks

### Intrinsic Locks (Monitor Locks)

Intrinsic locks, also known as monitor locks, are built-in synchronization mechanisms in Java that are implicitly associated with every object. They are used to synchronize access to an object's methods or blocks of code.

#### Characteristics of Intrinsic Locks:

- **Built-in Synchronization:** Intrinsic locks are built into every Java object and are used to synchronize access to object methods and blocks.
- **Usage:** Synchronization is achieved using the synchronized keyword on methods or blocks.
- **Reentrancy:** Intrinsic locks are reentrant, meaning a thread can acquire the same lock multiple times without deadlock if it already holds the lock.
- **Wait-Notify Mechanism:** Intrinsic locks use `Object.wait()`, `Object.notify()`, and `Object.notifyAll()` methods for inter-thread communication.
- **Limitations**: Intrinsic locks do not support advanced features like lock fairness, interruptible lock acquisition, or try-lock capabilities.

```java
public synchronized void synchronizedMethod() {
        // Critical section
        }
```

```java
synchronized (lockObject) {
        // Critical section
        }
```

## Explicit Locks
Explicit locks, such as `ReentrantLock.class`, are objects created explicitly in code and provide more advanced features.

### Characteristics of Explicit Locks:

- **Advanced Features**: Explicit locks, such as ReentrantLock, provide more control and flexibility than intrinsic locks, including features like fairness policies, interruptible lock acquisition, and try-lock capabilities.
- **Usage**: Explicit locks are used by creating an instance of a lock class and explicitly locking and unlocking it within the code.
- **Condition Variables:** Explicit locks use Condition objects for inter-thread communication, providing a richer set of waiting and notification methods than the wait-notify mechanism.

### Some Advanced Explicit Locks in Java:

#### ReentrantLock
- `ReentrantLock` is a mutual exclusion lock with the same basic behavior as the implicit locks used by synchronized methods and blocks.
- It provides additional features such as
  - Ability to check if the lock is held
  - Acquiring the lock without blocking
  - Fairness policies.

```java
private final ReentrantLock lock = new ReentrantLock();

public void method() {
    lock.lock();
    try {
        // Critical section
    } finally {
        lock.unlock();
    }
}
```

#### ReentrantReadWriteLock
- `ReentrantReadWriteLock` is a lock that allows multiple threads to read a resource concurrently while ensuring exclusive access for writing.
- It consists of a pair of locks: a read lock and a write lock.

```java
private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
private final Lock readLock = rwLock.readLock();
private final Lock writeLock = rwLock.writeLock();

public void readMethod() {
    readLock.lock();
    try {
        // Read operation
    } finally {
        readLock.unlock();
    }
}

public void writeMethod() {
    writeLock.lock();
    try {
        // Write operation
    } finally {
        writeLock.unlock();
    }
}
```

#### StampedLock
- `StampedLock` is a lock with three modes: reading, writing, and optimistic reading.
- It provides more flexibility and can offer better performance than `ReentrantReadWriteLock` in scenarios with many reader threads and few writer threads.

```java
private final StampedLock lock = new StampedLock();

public void readMethod() {
    long stamp = lock.readLock();
    try {
        // Read operation
    } finally {
        lock.unlockRead(stamp);
    }
}

public void writeMethod() {
    long stamp = lock.writeLock();
    try {
        // Write operation
    } finally {
        lock.unlockWrite(stamp);
    }
}
```
#### Lock Interface
The Lock interface provides the foundation for lock implementations, defining methods for acquiring, releasing, and querying the status of a lock.

#### Condition Interface
The Condition interface works with Lock instances to provide a way for threads to wait for specific conditions to be met before continuing execution.

```java
private final Lock lock = new ReentrantLock();
private final Condition condition = lock.newCondition();

public void awaitCondition() {
    lock.lock();
    try {
        // Wait for the condition
        condition.await();
        // Continue when the condition is met
    } finally {
        lock.unlock();
    }
}

public void signalCondition() {
    lock.lock();
    try {
        // Signal the condition
        condition.signalAll();
    } finally {
        lock.unlock();
    }
}
```

## Choosing Between Intrinsic and Explicit Locks

- **Simplicity vs. Flexibility:** Use intrinsic locks for simple synchronization needs where built-in synchronization is sufficient. Use explicit locks when you need more advanced features or finer control over locking behavior.
- **Performance:** Explicit locks can offer better performance in some scenarios, especially when using features like lock fairness or when dealing with high contention.