package ex6_work;

import java.util.Scanner;

public class WorkMain {
    
    public static void main(String[] args) {
        
        //정수를 키보드에서 받는다
        //정수 : 9
        //9은(는) 소수가 아닙니다

        //정수 : 7 
        //7은(는) 소수입니다

        //클래스 나누는것도 이해 안되고 클래스를 안나눠도 이해가 안돼
        Scanner sc = new Scanner(System.in);
        System.out.print( "정수 : " );
        int n = sc.nextInt();

        WorkSub ws = new WorkSub();
        boolean res = ws.isPrime(n);
        
        if (res) //res == true
            System.out.println(n + "은 소수");
        else
            System.out.println(n + "안 소수");
    }//main

}
