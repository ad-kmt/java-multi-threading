# Daemon Threads in Java
## Introduction
In Java, threads can be categorized into two types:
- user threads
- daemon threads

Daemon threads are special threads that run in the background and provide support to user threads. They are typically used for background tasks such as garbage collection, finalization, or other housekeeping functions.

## Characteristics of Daemon Threads

**Background Execution**:

Daemon threads run in the background and are not meant to perform core application logic. They are usually used for auxiliary or support tasks.

**Life Span: **

A daemon thread's life span is tied to the application's life span. It does not prevent the JVM from exiting. Once all user threads have finished execution, the JVM terminates, and all remaining daemon threads are stopped abruptly.

**Creation and Configuration**:

A thread can be marked as a daemon thread by calling the setDaemon(true) method on the Thread object before starting the thread. By default, threads are created as user threads.