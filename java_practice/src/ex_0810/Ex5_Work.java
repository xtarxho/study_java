package ex_0810;

import java.util.Scanner;

public class Ex5_Work {
    public static void main(String[] args) {

        //공책에 적기
        // 0811 숙제

        // 키보드에서 입력받은 두 수의 최소공배수
        // --------------
        // 수1 : 6
        // 수2 : 10
        // 최소공배수 : 30

        Scanner sc = new Scanner(System.in);

        System.out.print("수1 : ");
        int su1 = sc.nextInt();
        System.out.print("수2 : ");
        int su2 = sc.nextInt();

        for( int i = 1; i <= su1; i++ ){
            if (su1 * i % su2 == 0) {
                System.out.println(su1 * i);
                break;
            }

        }


    }// main
}
