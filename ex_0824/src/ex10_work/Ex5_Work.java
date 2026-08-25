package ex10_work;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex5_Work {
    public static void main(String[] args) {
        
        //나누기 연산만을 하는 계산식을 만들어서 결과를 완성하기
        //(연산결과를 담는 변수는 float이 아닌 int로 하자)
        //-------------------------------------
        //수1 : 10
        //수2 : 2
        //결과 : 10 / 2 = 5

        //수1 : 10
        //수2 : 0
        //오류 : 0으로 나눌 수 없습니다

        //수1 : abc
        //오류 : 숫자가 아닙니다

        //InputMismatchException -> 숫자가 아닐시에 나는 오류
        //ArithmeticException -> 0으로 나눌 때 나오는 오류

        Scanner sc = new Scanner(System.in);
        
        //int su2 = 0; -> 전역변수를 굳이 만들필요 없음 
        try{
            System.out.print( "수1 : " );
            int su1 = sc.nextInt();
    
            System.out.print( "수2 : " );
            int su2 = sc.nextInt();
    
            System.out.printf( "%d / %d = %d",su1,su2,su1/su2 );

        }catch(InputMismatchException e){
            System.out.println("숫자가 아닙니다");
        
        }catch(ArithmeticException e){
            System.out.println("0으로 나눌 수 없습니다");
           // System.out.println(su2 + "로 나눌 수 없습니다"); -> su2는 지역변수이기 때문에 전역변수로 하나 만들었지만 굳이 할 필요 없음

        }

    }//main
}
