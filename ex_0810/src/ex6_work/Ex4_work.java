package ex6_work;

import java.util.Scanner;

public class Ex4_work {
    public static void main(String[] args) {

        // 0811 숙제

        // 키보드에서 입력받은 두 개의 수의 최대공약수 출력
        // -----------------------
        // 수1 : 18
        // 수2 : 12
        // 최대공약수 : 6

        Scanner sc = new Scanner(System.in);

        /*
         * System.out.print( "수1 : " );
         * int su1 = sc.nextInt();
         * System.out.print( "수2 : " );
         * int su2 = sc.nextInt();
         * out : for( int i = su1; i >= 1; i-- ){
         * 
         * if( su1 % i == 0 && su2 % i == 0){
         * System.out.printf("최대공약수 : %d ", i);
         * break out;
         * }
         * 
         * 
         * }
         * System.out.println();
         */


        //쌤이 알려주신 방법
        System.out.print("수1 : ");
        int su3 = sc.nextInt();
        System.out.print("수2 : ");
        int su4 = sc.nextInt();
        
        if (su3 > su4) {
            int su5 = su3;
            su3 = su4;
            su4 = su5;            
        }
            int i = su3;

            for (; i >= 1; i--){

                if (su3 % i == 0 && su4 % i == 0) {
                    break;
                }
            }

            System.out.println("최대공약수 : " + i);
        

        System.out.println("-------------------------------");

        //유클리드 호제법으로 최대공약수 구하기
        // while(su4 != 0){
            
            
        //     int tmp = su3 % su4;
        //     su3 = su4;
        //     su4 = tmp;

        // }
        // System.out.println("최대공약수 : " + su3);


    }// main
}
