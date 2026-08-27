package ex8_homework;

import java.util.List;
import java.util.Random;

public class WorkSub extends Thread{
    
    private List<String> arr;
    private String[] data;
    private Random rnd;

    //생성자
    public WorkSub( List<String> arr, String[] data ){
        //얕은 복사
        this.arr = arr;
        this.data = data;
        rnd = new Random();
    }

    //스레드를 만들면 run 무조건 만들기 -> 공식
    @Override
    public void run() {
        
        while (true) {
            
            int random =rnd.nextInt(data.length);
            arr.add(data[random]);

            try{
                Thread.sleep(3000);
            }catch(Exception e){

            }


        }

    }


}
