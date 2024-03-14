# Difference between Thread.start() and Thread.run() in Java

In Java multithreading, the Thread class provides two methods, start() and run(), that are often confused by beginners. Understanding the difference between these two methods is crucial for proper thread management.

### Thread.start() Method
- The `start()` method is used to initiate the execution of a new thread.
- When `start()` is called, the Java Virtual Machine (JVM) creates a new thread, and the `run()` method of the Thread class or its subclass is executed in that new thread.
- This method is essential for achieving concurrency, as it allows the program to execute multiple threads simultaneously.

### Thread.run() Method
- The `run()` method contains the code that constitutes the new thread's task.
- It is called by the JVM when the thread is started using the `start()` method.
- If the `run()` method is called directly, it will not start a new thread. Instead, the code inside the `run()` method will be executed in the current thread, just like a normal method call.