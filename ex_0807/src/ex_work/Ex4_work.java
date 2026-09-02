package ex_work;

import java.util.Scanner;

public class Ex4_work {
    public static void main(String[] args) {
        

        //1부터 키보드에서 받은 값(n)까지의 합을 출력
        //-------------
        //입력 : 5
        //결과 : (1+2+3+4+5) 15   나오게

        Scanner sc = new Scanner(System.in);
        System.out.print("입력 : ");
        int n = sc.nextInt();

        int sum = 0;
        for( int i = 1; i <= 5; i++ ){
            sum += i;
        }
        System.out.println("결과 : " + sum);    
        













        //복습하기
        // Scanner sc = new Scanner(System.in);
        // System.out.print( "입력 : " );
        // int n = sc.nextInt();
        // int result = 0;



        // for(int i = 1; i <= n; i++){ //n이 핵심 
        //     result += i;
        // }
        // System.out.println( "결과 : " + result);


    }


}
