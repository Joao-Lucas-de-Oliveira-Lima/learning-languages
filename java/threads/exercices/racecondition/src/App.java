import java.util.concurrent.locks.ReentrantLock;

public class App {
    private static ReentrantLock lock = new ReentrantLock();
    private static int count;
    public static void main(String[] args) throws Exception {
        first.start();
        second.start();

    }

    public static void increment(){
        try{
            lock.lock();
            count++;
            System.out.println(count);
        }finally{
            lock.unlock();
        }        
    }

    static Thread first = new Thread(() -> {
        for(int index=0; index<50; index++){
            increment();
        }
        
    });
    static Thread second = new Thread(() -> {
        for(int index=0; index<50; index++){
            increment();
        }
    });
}
