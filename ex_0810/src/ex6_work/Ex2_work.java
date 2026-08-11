package ex6_work;

import java.util.Scanner;

public class Ex2_work {
    public static void main(String[] args) {
        
        //1부터 사용자가 입력한 숫자까지
        //3의 배수의 갯수, 5의 배수의 갯수를 출력
        //--------------------------
        //정수 : 70
        //3의 배수 : 23
        //5의 배수 : 14

        Scanner sc = new Scanner(System.in);
        System.out.print("정수 : ");
        int num = sc.nextInt();

        int cnt3 = 0;
        int cnt5 = 0;

        for( int i = 1; i <= num; i++ ){

            if( i % 3 == 0 )
                cnt3++;
            
            if( i % 5 == 0 )
                cnt5++;
            
           

        }

        System.out.println("3의 배수 : " + cnt3);
        System.out.println("5의 배수 : " + cnt5);




    }//main
}
