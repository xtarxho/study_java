package ex1_thread;

public class ThreadMain {
    public static void main(String[] args) {
        
        //ts 스레드와 main스레드 할일을 독립적으로 실행한다
        ThreadSub ts = new ThreadSub(); //스레드서브에 있는 내용을 사용할 수 있게 해준다 (메모리 할당 받기)
        ts.start(); //스레드를 동작시키는 메서드이다 run메서드를 돌리고싶으면 반드시 start를 호출해야된다

        try{
        Thread.sleep(1500);
        }catch(Exception e){

        } 
        System.out.println("main스레드 종료");

    }//main
}
