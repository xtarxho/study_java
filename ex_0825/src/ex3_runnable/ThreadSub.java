package ex3_runnable;

public class ThreadSub implements Runnable { //Runnable 인터페이스를 구현해 놓은 것

    @Override
    public void run() {
        
        for( int i = 0; i < 10; i++ ){

            try{
                Thread.sleep(1000); //1초 텀을 준다
                System.out.println(i);
            }catch(Exception e){

            }

        }//for

    }
    
}
