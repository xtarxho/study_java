package ex_0812;

import java.util.Scanner;

public class Ex6_Work {
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

        Scanner sc = new Scanner(System.in);
        System.out.println("정수 :");

        int[] arr = new int[10];

        int cnt1 = 0;
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();

        }

        for (int i = 0; i < arr.length; i++) {
            int cnt2 = 0;
            for (int j = 0; j < arr.length; j++) {

                if (arr[i] == arr[j]) {
                    cnt2++;
                } // if

            } // in for
            if (cnt2 > cnt1) {
                cnt1 = cnt2;
                max = arr[i];
            } // if

        } // out for
        System.out.println("가장 많이 나온 수 : " + max);
        System.out.println("등장 횟수 : " + cnt1);
    }// main
}
