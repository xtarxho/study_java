package ex_0807;

import java.util.Scanner;

public class Ex2_Work {
    public static void main(String[] args) {

        // 주말 숙제

        // 키보드에서 입력받은 2~9사이의 값에 해당하는
        // 구구단을 출력
        // -------------------
        // 단 : 10
        // 2~9사이에 값을 입력하세요

        // 단 : 5
        // 5*1=5
        // 5*2=10
        // ....
        // 5*9=45


        //틀림 다시하기
        Scanner sc = new Scanner(System.in);
        System.out.print( "단 : " );
        int dan = sc.nextInt();

        for( int i = 1; i <= 9; i++ ){

            
            if (dan < 2 || dan >= 10) {
                System.out.println("2~9사이의 값 입력바람");
                break;
            }else{
                System.out.printf( "%d * %d = %d\n",dan, i, dan*i );

            }
            



        }



    }// main
}
