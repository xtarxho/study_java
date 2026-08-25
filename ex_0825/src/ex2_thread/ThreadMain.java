package ex2_thread;

public class ThreadMain {
    public static void main(String[] args) {
        
        ThreadSub1 ts1 = new ThreadSub1();
        ThreadSub2 ts2 = new ThreadSub2();

        //멀티태스킹
        //동시에 시작하는거기 때문에 2가 먼저나올 수 있고 1이 먼저 나올 수 있다
        ts1.start(); //1찍는 스레드
        ts2.start(); //2찍는 스레드 


    }//main
}
