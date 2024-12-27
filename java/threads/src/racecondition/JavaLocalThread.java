package racecondition;

// Solution for Race Condition problems
public class JavaLocalThread {

    // ThreadLocal object to store data that will be used by threads
    private ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);

    /**
     * Each thread creates a local copy of the ThreadLocal variable and uses it independently 
     * without affecting other threads. The value of `count` is isolated per thread.
     */
    public void executeInteractableOperation() throws InterruptedException {
        // First thread increments the local count 100 times
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                count.set(count.get() + 1);
            }
            System.out.println(String.format("Local count value in thread1: %s", count.get()));
        });

        // Second thread increments the local count 100 times
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                count.set(count.get() + 1);
            }
            System.out.println(String.format("Local count value in thread2: %s", count.get()));
        });

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for both threads to complete execution
        thread1.join();
        thread2.join();

        // The main thread's count value remains unchanged
        System.out.println(String.format("Count value in the main thread: %s", count.get()));
    }
}
