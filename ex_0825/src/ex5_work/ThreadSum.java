package ex5_work;

public class ThreadSum extends Thread {

    private int[] arr;

    public ThreadSum(int[] arr) {
        this.arr = arr;
    }

    int sum;

    @Override
    public void run() {

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        System.out.println("합 : " + sum);

    }

}
