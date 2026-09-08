package ex_0813;

import java.util.Scanner;

public class Ex3_Work {
    public static void main(String[] args) {

        // 키보드에서 입력받은 값보다 큰 첫번 째 요소를 찾아 출력해보기
        // ------------------------------------
        // 입력 : 8
        // 결과 : 10

        int[][] arr = { { 3, 1, 4, 7 },
                { 10, 2, 9, 3, 8 },
                { 7, 4, 1 } };

        Scanner sc = new Scanner(System.in);

        System.out.print("입력 : ");
        int num = sc.nextInt();

        int big = 0;

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr[i].length; j++) {

                if (num < arr[i][j]) {

                    if (big == 0 || arr[i][j] < big) {
                        big = arr[i][j];
                    }

                }

            }

        }
        System.out.println("결과 : " + big);

    }// main
}
