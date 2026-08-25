package ex5_work;

public class ThreadMain {
    public static void main(String[] args) {
        
        //배열 arr에 담긴 값의 합을 A스레드가 계산하고
        //arr에 담긴 값의 곱을 B스레드가 계산해서 출력하기
        //--------------------------
        //어떤 결과가 먼저 나올지 모른다 -> 합 곱
        //합 : 29
        //곱 : 3960

        //곱 : 3960
        //합 : 29
        int[] arr = {3,5,11,6,4};

        ThreadSum ts = new ThreadSum(arr); 
        ThreadMultifly tm = new ThreadMultifly(arr);
        
        ts.start(); // ThreadSum안에 있는 코드를 가져옴
        tm.start(); //ThreadMultifly안에 있는 코드를 가져옴

    }//main
}
