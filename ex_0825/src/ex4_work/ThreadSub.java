package ex4_work;

public class ThreadSub extends Thread{
    
    //이코드의 요점은 메인에서 받은 값을 서브로 전달하는 것 
    //생성자를 만든다
    private int n;
    public ThreadSub( int n ){ 
        this.n = n;
    }

    @Override
    public void run() { //오버라이딩 메서드여서 파라미터를 못받을 수 있다
        
        for( int i = n; i >= 0; i-- ){

            try{
                Thread.sleep(1000);
                System.out.println(i);
            }catch(Exception e){

            }

        }

    }

}
