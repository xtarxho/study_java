package ex5_work;

public class ThreadMultifly extends Thread{
    
    private int[] arr;

    //생성자를 만들어준다
    public ThreadMultifly(int[] arr){
        this.arr = arr;
    }

    @Override
    public void run() {
        int res = arr[0];
        for( int i = 1; i < arr.length; i++){
            res *= arr[i];

        }
        System.out.println("곱 : " + res);
    }

}
