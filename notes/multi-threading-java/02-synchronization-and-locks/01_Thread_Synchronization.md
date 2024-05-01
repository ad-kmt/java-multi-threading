# Thread Synchronization in Java
## Introduction
Thread synchronization in Java ensures that multiple threads can safely access shared resources without causing data inconsistency or other concurrency issues. It involves coordinating the execution of threads to prevent race conditions and maintain thread safety.

There are two types of thread synchronization mutual exclusive and inter-thread communication.

##### 1. Mutual Exclusive
- Synchronized method.
- Synchronized block.
- Static synchronization.

##### 2. Cooperation (Inter-thread communication)

## Mutual Exclusive
Mutual Exclusion helps keep threads from interfering with one another while sharing data.

#### Synchronized Methods
A method can be declared as synchronized, ensuring that only one thread can execute it at a time.
When a thread enters a synchronized method, it acquires the intrinsic lock (monitor lock) of the object for which the method was invoked.

```java
public synchronized void incrementCounter() {
        counter++;
        }
```

#### Synchronized Blocks
Synchronized blocks allow more fine-grained control over synchronization by specifying the object that should be locked.
Only one thread can execute the synchronized block for a given lock object at a time.

```java
synchronized (lockObject) {
        // Critical section
        }
```

## Inter-thread communication
Inter-thread communication in Java allows multiple threads to communicate and coordinate their actions, ensuring proper synchronization.

#### wait(), notify(), and notifyAll() Methods
These methods are used for **inter-thread communication** and must be called from within a synchronized context.

##### wait()
Causes the current thread to wait until another thread invokes notify() or notifyAll() on the same object.
##### notify()
Wakes up a single thread that is waiting on the object's monitor.
##### notifyAll()
Wakes up all threads that are waiting on the object's monitor.

## Locks
Synchronization is built around an internal entity known as the lock or monitor. Locks allow threads to have controlled access to shared resources.

Here's how it works:

##### 1. Locking
When a thread wants to execute a critical section of code, it tries to "lock" the object that contains that code. This is like saying, "I'm using this resource right now, please wait your turn."
##### 2. Exclusive Access
Once a thread has locked the object, no other thread can lock the same object until the first thread is done. This ensures that only one thread can use the resource at a time.
##### 3. Unlocking
When the thread is finished with the critical section, it "unlocks" the object, allowing other threads to lock it and use the resource.


#### Intrinsic Locks (Monitor Locks)
- Intrinsic locks are built-in synchronization mechanisms in Java that are implicitly associated with every object. They are used to synchronize access to an object's methods or blocks of code.
- Intrinsic locks are achieved using `synchronized` keyword on object's methods or code blocks

#### Explicit Locks in java.util.concurrent.locks
- Java provides explicit lock objects for more flexible synchronization, such as `ReentrantLock` , `ReentrantReadWriteLock` and `StampedLock`
- These locks support various features like:
  - Fairness
  - Acquiring lock without blocking
  - Interruptible lock acquisition.

## The 'volatile' keyword
In Java, the volatile keyword:

- is used to indicate that a variable's value will be modified by different threads.
- guarantees visibility of changes made to variables across threads.
- any read or write operation on the volatile variable is performed directly on the main memory, ensuring that all threads see the most recent value.

## Synchronization Best Practices
- Minimize the scope of synchronization to reduce contention.
- Prefer using high-level concurrency utilities over low-level synchronization.
- Avoid nested locks and synchronize only the critical section of code.
