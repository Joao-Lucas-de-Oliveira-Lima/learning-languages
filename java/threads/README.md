# Java Threads

## Creating a Thread in Java
Threads in Java can be created using the `Thread` class. Below is an example of creating a thread with an anonymous inner class:

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

## Creating a Thread Using Lambda Expression

In Java, you can create a thread using a lambda expression, which makes the code more concise and readable.

### Example:

```java
static Thread thread = new Thread(() -> {
    System.out.println("Something");
}, "Thread");

```

## Running the Thread
You can start the thread by calling its `start()` method:

```java
public class App {
    public static void main(String[] args) {
        Thread thread = new MyThread().getThread();
        thread.start(); // Output: Hello from this Thread!
    }
}
```

## Setting Thread Priorities
Thread priorities can be used to influence the order in which threads are scheduled for execution. Java allows setting priorities between `Thread.MIN_PRIORITY` (1) and `Thread.MAX_PRIORITY` (10). The default priority is `Thread.NORM_PRIORITY` (5):

```java
thread.setPriority(Thread.MAX_PRIORITY); // Highest priority
thread.setPriority(Thread.MIN_PRIORITY); // Lowest priority
thread.setPriority(8); // Arbitrary priority within the allowed range
```

> **Note:** Thread priorities are hints to the operating system and may not guarantee strict priority scheduling.

## Handling Exceptions in Threads
If an exception occurs in a thread, it should not affect the execution of other threads. To handle uncaught exceptions in a thread, you can use an `UncaughtExceptionHandler`:

### Creating an Exception Handler for a Thread
This example demonstrates how to handle uncaught exceptions in a specific thread:

```java
thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("An error occurred in thread: " + t.getName());
        e.printStackTrace();
    }
});
```

## Common Problems in Multithreaded Programming

### 1. Race Condition
Occurs when two or more threads attempt to modify a shared resource simultaneously, leading to unpredictable behavior or incorrect results.

#### Example:
```java
public class RaceCondition {
    private int count = 0;

    public void execute() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                count++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                count++;
            }
        });

        thread1.start();
        thread2.start();
    }
}
```

### 2. Deadlock
Occurs when two or more threads are waiting for each other to release resources, leading to a situation where none can proceed.

#### Example:
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
Occurs when one or more threads are perpetually denied access to resources because other threads monopolize them. This often happens with thread prioritization.

### 4. Data Corruption
Occurs when multiple threads modify a shared resource without proper synchronization, leading to inconsistent states or data loss.

#### Solution:
Use synchronization mechanisms like `synchronized` blocks, `ReentrantLock`, or thread-safe collections (e.g., `ConcurrentHashMap`).

### Preventing Common Issues

#### Using Locks
Locks like `ReentrantLock` provide fine-grained control over synchronization and can prevent issues like race conditions:

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
ThreadLocal variables ensure each thread has its own independent copy of a variable:

```java
private final ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);

public void increment() {
    count.set(count.get() + 1);
    System.out.println("Thread-local count: " + count.get());
}
```