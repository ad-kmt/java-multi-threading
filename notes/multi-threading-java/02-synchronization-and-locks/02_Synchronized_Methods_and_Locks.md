# Synchronized Methods and Locks in Java
## Introduction
In Java, synchronized methods are used to ensure that only one thread can execute a method on a given object at a time. This is crucial for maintaining data consistency and avoiding race conditions when multiple threads access shared resources.

```java
public synchronized void incrementCounter() {
    counter++;
}
```
## How Synchronized Methods Work
When a method is declared as synchronized, it acquires an intrinsic lock (also known as a monitor lock) on the object for which it is invoked. This lock is automatically released when the method completes, either normally or through an exception.

## Locking Mechanism
- **Object-Level Lock:** Each object in Java has an associated monitor lock. When a thread enters a synchronized instance method, it acquires the lock on the object instance (this). For static synchronized methods, the thread acquires the lock on the class object (Class object).
- **Reentrant Locking:** The intrinsic lock is reentrant, meaning that the same thread can acquire the same lock multiple times without causing a deadlock.

## Synchronized Methods and Multiple Threads
If two or more synchronized methods are defined in a class, only one thread can execute any of these methods at a time for a given object instance. Other threads attempting to execute any synchronized method on the same object will be blocked until the current thread releases the lock.

However, different threads can concurrently execute synchronized methods on different instances of the same class, as each object has its own lock.

## Example: Bank Account Transactions
Consider a simple BankAccount class with synchronized methods for depositing and withdrawing money:

```java
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        balance -= amount;
    }
}
```
In this example, if one thread is executing the deposit method on an instance of BankAccount, no other thread can execute either the deposit or withdraw method on the same instance until the first thread completes its execution.

## Best Practices
- Use synchronized methods judiciously, as excessive synchronization can lead to contention and reduce performance.
- Consider using other concurrency utilities from the java.util.concurrent package for more fine-grained control over synchronization.