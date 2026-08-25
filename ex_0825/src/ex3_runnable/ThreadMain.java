package ex3_runnable;

public class ThreadMain {
    public static void main(String[] args) {
        
        //Runnable 인터페이스를 호출하는 방법
        ThreadSub ts = new ThreadSub();
        Thread t = new Thread(ts); //괄호 안에 runnable 객체를 준다 -> ts
        t.start();

    }//main
}
