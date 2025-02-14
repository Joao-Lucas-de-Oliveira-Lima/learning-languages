package extendingthread;

public class MyCustomThread extends Thread{

    @Override
    public void run() {
        System.out.println("Extending thread!");
        super.run();
    }
    
}
