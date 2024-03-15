# General Concepts in Multithreading

## Concurrency and Parallelism

**Concurrency**: The ability of a program to execute multiple tasks simultaneously but not necessarily at the same time. It's about dealing with multiple tasks at once, such as managing multiple users or requests.

**Parallelism**: The ability of a program to perform multiple tasks at the exact same time by utilizing multiple processors or cores.

## Multithreading vs Multiprocessing

**Multithreading**: Use multithreading when you want to perform multiple tasks concurrently within the same application, sharing the same memory space. It's suitable for tasks that are I/O-bound or require frequent context switches.

**Multiprocessing**: Use multiprocessing when you want to perform parallel tasks that are CPU-bound and can benefit from multiple processors or cores. It's suitable for tasks that require isolation and separate memory space for each process.

## Race Condition

A race condition is a concurrency issue that occurs in a multithreaded or multiprocess environment when two or more threads or processes attempt to access and modify shared data simultaneously, leading to unpredictable and incorrect results.