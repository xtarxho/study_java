package ex_0810;

import java.util.Scanner;

public class Ex4_Work {
    public static void main(String[] args) {

        //공책에 적기
        // 0811 숙제

        // 키보드에서 입력받은 두 개의 수의 최대공약수 출력
        // -----------------------
        // 수1 : 18
        // 수2 : 12
        // 최대공약수 : 6

        Scanner sc = new Scanner(System.in);

        System.out.print( "수1 : " );
        int su1 = sc.nextInt();
        System.out.print( "수2 : " );
        int su2 = sc.nextInt();
        
        if (su1 > su2) {
            int tmp = su1;
            su1 = su2;
            su2 = tmp;
        }

        int i = su1;
        for(; i < su2; i--){

            if (su1 % i == 0 && su2 % i == 0) {
                System.out.println("최대공약수 : " + i);
                break;
            }

        }


    }// main
}
