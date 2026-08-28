package ex_0810;

import java.util.Scanner;

public class Ex6_Work {
    public static void main(String[] args) {

        // 0811 숙제

        // 키보드에서 출력될 수열의 갯수를 지정하고
        // 피보나치수열대로 결과 보여주기
        // ----------------------
        // 입력 : 7 (키보드에서 7 입력 받으면 7개까지 나오면 됨 13까지)
        // 1 1 2 3 5 8 13 21 34 55 -> 피보나치수열

        Scanner sc = new Scanner(System.in);
        System.out.print( "입력 : " );
        int cnt = sc.nextInt();

        int n1 = 1;
        int n2 = 0;
        int n3 = 0;

        for( int i = 0; i < cnt; i++ ){

            n2 = n3;
            n3 = n1;
            n1 = n2 + n3;

            System.out.print(" " + n3);

        }

    }// main
}
