# Java Threads

## Overview
This guide provides an introduction to working with threads in Java, covering thread creation, execution, prioritization, exception handling, and common concurrency issues.

## Creating a Thread in Java
### Extending the `Thread` Class
The simplest way to create a thread in Java is by extending the `Thread` class and overriding the `run` method:

```java
public class MyCustomThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from this thread!");
    }
}
```

### Implementing the `Runnable` Interface
Another way is to implement the `Runnable` interface and pass an instance to a `Thread` object:

```java
public class MyThread {
    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("Hello from this Thread!");
        }
    });

    public Thread getThread() {
        return this.thread;
    }
}
```

## Creating a Thread Using a Lambda Expression
Java allows creating threads using lambda expressions, making the code more concise:

```java
Thread thread = new Thread(() -> {
    System.out.println("Lambda thread execution");
}, "LambdaThread");
```

## Running a Thread
To start a thread, call its `start()` method:

```java
public class App {
    public static void main(String[] args) {
        Thread thread = new MyThread().getThread();
        thread.start(); // Output: Hello from this Thread!
    }
}
```

## Setting Thread Priorities
Threads in Java have priorities ranging from `Thread.MIN_PRIORITY` (1) to `Thread.MAX_PRIORITY` (10). The default priority is `Thread.NORM_PRIORITY` (5):

```java
thread.setPriority(Thread.MAX_PRIORITY); // Highest priority
thread.setPriority(Thread.MIN_PRIORITY); // Lowest priority
thread.setPriority(8); // Custom priority
```

> **Note:** Thread priorities are hints to the operating system and do not guarantee execution order.

## Handling Exceptions in Threads
Threads should handle exceptions independently to avoid affecting other threads. Use `UncaughtExceptionHandler`:

```java
thread.setUncaughtExceptionHandler((t, e) -> {
    System.out.println("Exception in thread: " + t.getName());
    e.printStackTrace();
});
```

## Common Problems in Multithreaded Programming
### 1. Race Condition
Occurs when multiple threads modify a shared resource simultaneously:

```java
public class RaceCondition {
    private int count = 0;

    public void execute() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) count++;
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) count++;
        });

        thread1.start();
        thread2.start();
    }
}
```

### 2. Deadlock
Occurs when threads wait indefinitely for each other's resources:

```java
public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("Method 1 acquired both locks.");
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("Method 2 acquired both locks.");
            }
        }
    }
}
```

### 3. Starvation
Occurs when low-priority threads are perpetually denied execution due to high-priority threads monopolizing resources.

### 4. Data Corruption
Happens when multiple threads modify shared data without synchronization.

#### Solution: Synchronization Mechanisms
Use `synchronized` blocks, `ReentrantLock`, or thread-safe collections like `ConcurrentHashMap`.

#### Using Locks

```java
private final ReentrantLock lock = new ReentrantLock();
private int count = 0;

public void increment() {
    lock.lock();
    try {
        count++;
    } finally {
        lock.unlock();
    }
}
```

#### Using ThreadLocal
Each thread gets its own independent variable instance:

```java
private final ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);

public void increment() {
    count.set(count.get() + 1);
    System.out.println("Thread-local count: " + count.get());
}
```