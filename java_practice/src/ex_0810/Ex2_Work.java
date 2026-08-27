package ex_0810;

import java.util.Scanner;

public class Ex2_Work {
    public static void main(String[] args) {
        
        //1부터 사용자가 입력한 숫자까지
        //3의 배수의 갯수, 5의 배수의 갯수를 출력
        //--------------------------
        //정수 : 70
        //3의 배수 : 23
        //5의 배수 : 14

        Scanner sc = new Scanner(System.in);

        System.out.print( "정수 : " );
        int n = sc.nextInt();

        int cnt1 = 0;
        int cnt2 = 0;
        for( int i = 1; i <= n; i++ ){

            if (i % 3 == 0 ) {
                cnt1++;
            } 
            if(i % 5 == 0){
                cnt2++;
            }
        }
        System.out.println("3의 배수 : " + cnt1);
        System.out.println("5의 배수 : " + cnt2);
    }//main
}
