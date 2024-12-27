package externalthread;

// A class that encapsulates a Thread
public class ExternalThread {
    private final Thread thread = new Thread(() -> {
        System.out.println("Hello from a thread created in its own package!");
    }, "ExternalThread");

    // Provides access to the encapsulated thread
    public Thread getThread() {
        return this.thread;
    }
}
