package ex_work;

import java.util.Scanner;

public class Ex2_work {
    public static void main(String[] args) {
        
        //주말 숙제

        //키보드에서 입력받은 2~9사이의 값에 해당하는
        //구구단을 출력
        //-------------------
        //단 : 10
        //2~9사이에 값을 입력하세요

        //단 : 5
        //5*1=5
        //5*2=10
        //....
        //5*9=45

        Scanner sc = new Scanner(System.in);

        System.out.print("단 : ");
        int dan = sc.nextInt();

        //내가 푼 방법
        /*for( int i = 1; i <= 9; i++ ){
            System.out.println(dan+"*"+i+"="+ dan*i  );

        }*/
        //쌤이 알려주신 방법
        if( dan <= 1 || dan >= 10 ){
            System.out.println("2 ~ 9사이의 값을 넣으세요");
        }else{
            for( int i = 1; i <= 9; i++ ){
                System.out.printf( "%d * %d = %02d\n",dan, i, (dan*i)  ); //formatter 활용해서 문제 다르게 풀어봄

            }//for

        }
        
        


        // 1. 키보드에서 두 개의 정수를 입력받아, 두 수의 합, 차, 곱, 몫, 나머지를 출력하는 프로그램을 작성하세요.
        Scanner sc1 = new Scanner(System.in);

        System.out.print("수1 : ");
        int a = sc1.nextInt();

        System.out.print("수2 : ");
        int b = sc1.nextInt();

        System.out.println( a + "+" + b + "=" + (a+b) );
        System.out.println( a + "-" + b + "=" + (a-b) );
        System.out.println( a + "*" + b + "=" + a*b );
        System.out.println( a + "/" + b + "=" + a/b );
        System.out.println( a + "%" + b + "=" + a%b );
        
        // 2. 1에서 100까지의 숫자 중 3의 배수만 출력하는 프로그램을 작성하세요.

        // 3. 키보드에서 한 개의 정수를 입력받아, 입력한 수가 소수(prime number)인지 아닌지 판별하는 프로그램을 작성하세요.

    }//main
}
