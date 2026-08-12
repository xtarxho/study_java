package ex_work;

import java.util.Scanner;

public class Ex06_work {
    public static void main(String[] args) {

        // 키보드에서 정수를 10개 입력받아 배열에 담고
        // 가장 많이 등장한 정수와 등장 횟수를 출력
        // ---------------------------------
        // 정수 :
        // 1
        // 1
        // 2
        // 2
        // 2
        // 3
        // 4
        // 2
        // 5
        // 6
        // 가장 많이 등장한 숫자 : 2
        // 등장횟수 : 4

        // 분석용 코드로 나쁘지 않다 분석해보기

        Scanner sc = new Scanner(System.in);
        System.out.print("정수 : ");

        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();

        } // for
        int count = 0;
        int max = 0;

        for (int i = 0; i < arr.length; i++) {

            int cnt = 0;

            for (int j = 0; j < arr.length; j++) {

                if (arr[i] == arr[j]) {
                    cnt++;
                }

            } // inner

            if (cnt > count) {
                count = cnt;
                max = arr[i];

            }

        } // outer

        System.out.println("가장 많이 등장 : " + max);
        System.out.println("등장 횟수 : " + count);

    }// main
}
