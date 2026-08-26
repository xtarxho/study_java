package ex_work;

public class TimerSub extends Thread{
    
    private int timer = 0;
    private boolean isCheck = true;

    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public void run() {
        
        while ( isCheck ) { // isCheck가 참일때라는 뜻

            try{

                Thread.sleep(1000);
                timer++;


            }catch(Exception e){

            }
            
        }//while

        System.out.println(timer + "초");

    }

}
