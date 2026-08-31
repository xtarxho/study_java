package ex_0812;

public class Ex3_Work {
    public static void main(String[] args) {
        
        //배열 arr에 담긴 값 중 가장 큰 값을 출력
        // 가장 큰 값 : 19
        int[] arr = {4, 13, 7, 19, 1, 15};

        int max = arr[0];
        for( int i = 0; i < arr.length; i++ ){

            if (max < arr[i]) {
                max = arr[i];
            }
        }
        System.out.print(max);
    }//main
}
