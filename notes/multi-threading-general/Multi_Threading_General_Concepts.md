# General Concepts in Multithreading

## Concurrency and Parallelism

**Concurrency**: The ability of a program to execute multiple tasks simultaneously but not necessarily at the same time. It's about dealing with multiple tasks at once, such as managing multiple users or requests.

**Parallelism**: The ability of a program to perform multiple tasks at the exact same time by utilizing multiple processors or cores.

## Multithreading vs Multiprocessing

**Multithreading**: Use multithreading when you want to perform multiple tasks concurrently within the same application, sharing the same memory space. It's suitable for tasks that are I/O-bound or require frequent context switches.

**Multiprocessing**: Use multiprocessing when you want to perform parallel tasks that are CPU-bound and can benefit from multiple processors or cores. It's suitable for tasks that require isolation and separate memory space for each process.

## Race Condition

A race condition is a concurrency issue that occurs in a multithreaded or multiprocess environment when two or more threads or processes attempt to access and modify shared data simultaneously, leading to unpredictable and incorrect results.

## Contention

Contention refers to a situation where multiple threads are competing for the same shared resources, such as locks, memory, or CPU time. High contention occurs when many threads try to access the same resource simultaneously, leading to increased waiting times and reduced overall performance.

## Deadlocks

A deadlock is a situation in a multithreading environment where two or more threads are blocked forever, each waiting for the other to release a resource. Deadlocks are a common issue in concurrent programming and can lead to a complete standstill of an application.

## Starvation

Starvation is a problem in multithreaded environments where one or more threads are perpetually denied access to shared resources or the CPU, preventing them from making progress in their execution. 

## Livelock

Livelock is a situation in concurrent programming where two or more threads are actively responding to each other's actions, but none can make progress and complete their tasks.


## Preemption
Preemption is a fundamental concept in multitasking and multithreading environments where multiple tasks or threads compete for CPU time.

It refers to the ability of the operating system or a scheduler to interrupt the currently executing task or thread and switch the CPU's attention to another task or thread that requires immediate processing.