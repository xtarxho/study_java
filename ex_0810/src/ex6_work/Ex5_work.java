package ex6_work;

import java.util.Scanner;

public class Ex5_work {
    public static void main(String[] args) {

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

        
        for( int i = 1; i <= su1; i++){

            if( su1 * i % su2 == 0 ){
                System.out.printf( "최소공배수 : %d" , su1 * i );
            }
        }
        System.out.println(); 

        // for( int i = 1; i <= su1 * su2; i++){

        //     if( i % su1 == 0 && i % su2 == 0 ){
        //         System.out.println("최소공배수 : " + i);
        //         break;
        //     }

        // }

        System.out.println("--------------------------------");

        //최소공배수 (유클리드 호제법)
        int x = su1;
        int y = su2;

        while (y != 0) {
            int tmp = x % y;
            x = y;
            y = x;
            y = tmp;

        }

        int gcd = x; //최대공약수

        //최소공배수
        int lcm = (su1 * su2) / gcd;
        System.out.println("최소공배수 : " + lcm);


    }// main
}
