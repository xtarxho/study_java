package ex2_thread;

public class ThreadSub1 extends Thread{
    
    @Override
    public void run() {
        
        for( int i = 0; i < 100; i++ ){
            System.out.print("1");
        }

    }
}
