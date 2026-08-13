package ex_work;

import java.util.Scanner;

public class Ex03_work {
    public static void main(String[] args) {
        // 키보드에서 입력받은 값보다 큰 첫번 째 요소를 찾아 출력해보기
        // ------------------------------------
        // 입력 : 8
        // 결과 : 10
        Scanner sc = new Scanner(System.in);
        System.out.print("입력 : ");
        int q = sc.nextInt();

        int big = 0;
        int[][] arr = { { 3, 1, 4, 7 },
                { 10, 2, 9, 3, 8 },
                { 7, 4, 1 } };

        out: for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr[i].length; j++) {

                if (arr[i][j] > q) {
                    big = arr[i][j];
                    break out;
                }

            }

        }
        System.out.printf("결과 : %d", big);

        /*
         * //쌤이 알려주신 방법
         * out : for( int i = 0; i < arr.length; i++ ){
         * 
         * for( int j = 0; j < arr[i].length; j++ ){
         * 
         * if(arr[i][j] > q){
         * System.out.println("결과 : " + arr[i][j]);
         * break out;
         * }
         * 
         * 
         * }
         * 
         * 
         * }
         * 
         */

    }// main
}
