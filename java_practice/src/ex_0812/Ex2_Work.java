package ex_0812;

public class Ex2_Work {
    public static void main(String[] args) {

        // 배열 arr에 담긴 모든 값의 합을 출력
        // 결과 : 121

        int[] arr = { 10, 11, 37, 61, 2 };

        int total = 0;
        for( int i = 0; i < arr.length; i++ ){
            total += arr[i];
        }
        System.out.println(total);
    }// main
}
