package racecondition;

// Example of a race condition scenario
public class RaceCondition {
    private Integer count = 0;

    /**
     * Executes two threads 100 times in parallel.
     * The join command ensures that the method `executeConcurrentOperation`
     * proceeds only after the execution of the threads is complete.
     *
     * To create threads inside a loop, they must be instantiated within the loop.
     * Otherwise, reusing the same thread after execution will result in an exception.
     * To avoid this issue, a new thread must be created for each iteration.
     */
    public void executeConcurrentOperation() throws InterruptedException {
        for (int iteration = 0; iteration < 100; iteration++) {
            // Thread to increment count 100 times
            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    count++;
                }
            });

            // Another thread to increment count 100 times
            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    count++;
                }
            });

            // Start both threads
            thread1.start();
            thread2.start();

            // Wait for both threads to complete
            thread1.join();
            thread2.join();

            // Display the value of count after each iteration
            System.out.printf("Value of count in iteration %d: %d%n", iteration + 1, count);

            // Reset the counter for the next iteration
            count = 0;
        }
    }
}
