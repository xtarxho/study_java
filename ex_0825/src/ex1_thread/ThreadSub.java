package ex1_thread;

public class ThreadSub extends Thread { // 스레드 상속을 무조건 해줘야한다
    
    // 스레드의 가장 기본적인 형태이다
    // run을 오버라이딩을 해줘야한다
    @Override // -> 부모가 준거랑 다르게 내용을 재정의하는 것
    public void run() { // -> run메서드는 독립적으로 따로 사용할 수 있게 해준다
        // 프로세스의 독립적인 수행을 위한 영역

        for (int i = 0; i < 10; i++) {

            // 대표적으로 try-catch를 사용해야하는 메서드이다
            // 괄호안에 500을 넣으면 0.5초 동안 휴식을 한다
            try {
                Thread.sleep(500); // sleep이라는 static 메서드를 호출한다 -> 강제로 쉬게 만드는 메서드
            } catch (Exception e) {

            }

            System.out.println("스레드 실행중");

        }

    }

}
