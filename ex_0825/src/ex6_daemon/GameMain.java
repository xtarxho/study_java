package ex6_daemon;

public class GameMain {
    public static void main(String[] args) {

        //데몬스레드
        //다른스레드의 작업을 돕는 보조적인 역할을 수행하는 스레드
        //주 스레드가 끝나면 같이 끝난다
        //메인스레드가 종료되면 데몬스레드도 강제로 종료된다

        SaveCls sc = new SaveCls();
        //setDaemon을 통해 SaveCls를 멈출 수 있게 해준다
        sc.setDaemon(true); //sc스레드를 데몬스레드로 지정하는 것
        sc.start(); //3초 간격으로 게임을 저장

        //main 스레드는 20초동안 게임을 진행한다
        for (int i = 0; i < 20; i++) {

            try{
                Thread.sleep(1000);
                System.out.println("게임중....");

            }catch(Exception e){

            }

        } // for

    }// main
}
