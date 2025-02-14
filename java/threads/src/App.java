import extendingthread.MyCustomThread;
import externalthread.ExternalThread;
import racecondition.JavaLocalThread;
import racecondition.JavaReentrantLock;

public class App {
    public static void main(String[] args) throws Exception {
        // Calling a thread created in another package
        Thread externalThread = new ExternalThread().getThread();
        // Setting priority for the thread
        externalThread.setPriority(Thread.MAX_PRIORITY);

        // Creating an exception handler for threadException
        threadException.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("An exception occurred in thread: " + t.getName());
            e.printStackTrace();
        });

        // Starting threads
        thread.start();
        threadException.start();
        externalThread.start();

        // Testing race conditions
        /*
        RaceCondition raceCondition = new RaceCondition();
        raceCondition.executeConcurrentOperation();

        JavaLocalThread javaLocalThread = new JavaLocalThread();
        javaLocalThread.executeInteractableOperation();
        */

        JavaReentrantLock javaReentrantLock = new JavaReentrantLock();
        javaReentrantLock.executeInteractableOperation();

        MyCustomThread myCustomThread = new MyCustomThread();
        myCustomThread.start();
        
    
    }

    // Declaring a basic Thread
    static Thread thread = new Thread(() -> System.out.println("Hello, from this Thread!"), "BasicThread");

    // Declaring a Thread that throws an exception
    static Thread threadException = new Thread(() -> {
        throw new RuntimeException("Exception from thread!");
    }, "ExceptionThread");

}
