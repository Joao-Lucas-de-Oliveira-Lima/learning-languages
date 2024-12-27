package racecondition;

import java.util.concurrent.locks.ReentrantLock;

// Using a lock to prevent race conditions
// A lock is applied to control access to the shared resource 'count', ensuring that only one thread can 
// increment it at a time. The ReentrantLock is used to handle this synchronization.
public class JavaReentrantLock {
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private int count = 0;

    public void executeInteractableOperation() throws InterruptedException {
        for (int iteration = 0; iteration < 100; iteration++) {
            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    // Acquiring the lock
                    reentrantLock.lock();
                    try {
                        count++;
                    } finally {
                        // Releasing the lock
                        reentrantLock.unlock();
                    }
                }
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    // Acquiring the lock
                    reentrantLock.lock();
                    try {
                        count++;
                    } finally {
                        // Releasing the lock
                        reentrantLock.unlock();
                    }
                }
            });

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            // Printing the current value of count
            System.out.printf("Iteration %d: Value of count = %d%n", iteration + 1, count);
            // Resetting count for the next iteration
            count = 0;
        }
    }
}
