package ex_0806;

import java.util.Scanner;

public class Ex4_Work {
    public static void main(String[] args) {

        // 문제 -> 정답을 적어주고 정답처럼 나오게 코드 작성하기
        // 수1 : 10
        // 수2 : 20
        // 연산자 : +
        // 10 + 20 = 30

        Scanner sc = new Scanner(System.in);

        System.out.print( "수1 : " );
        int su1 = sc.nextInt();

        System.out.print( "수2 : " );
        int su2 = sc.nextInt();

        System.out.print( "연산자 : " );
        String op = sc.next();

        switch (op) {
            case "+":
                System.out.printf( "%d + %d = %d",su1, su2, su1+su2 );
                break;
            case "-":
                System.out.printf( "%d - %d = %d",su1, su2, su1-su2 );
                break;
            case "*":
                System.out.printf( "%d * %d = %d",su1, su2, su1*su2 );
                break;
            case "/":
                //소수를 사용할거면 %f를 사용해야한다
                System.out.printf( "%d / %d = %f",su1, su2, (float)su1/su2 );
                break;
        
            default:
                System.out.println("연산자 오류");
                break;
        }

    }// main
}
