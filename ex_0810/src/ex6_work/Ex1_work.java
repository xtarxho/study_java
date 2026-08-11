package ex6_work;

import java.util.Scanner;

public class Ex1_work {
    public static void main(String[] args) {
        
        //1부터 100까지의 숫자중에서
        //키보드에서 입력받은 수 보다 큰 첫번째
        //3의 배수를 찾아 출력
        //-----------------------
        //입력 : 55
        //55보다 큰 첫번째 3의 배수 : 57
        
        //쌤이 알려주신 방법
       /*  Scanner sc = new Scanner(System.in);
        System.out.print( "입력 : ");
        int num = sc.nextInt();
        
        out : for( int i = num + 1; i <= 100; i++ ){

            if ( i % 3 == 0 ) {
                System.out.printf( "%d보다 큰 첫번째 3의 배수 : %d", num, i );
                break out;
            }
            

        } */

            //내가 만든 방법
            Scanner sc = new Scanner(System.in);

            System.out.print("입력 : ");
            int num = sc.nextInt();
            
            out: for (int i = 1; i <= 100; i++) {
            
                if (i > num && i % 3 == 0) {
                    System.out.printf("%d보다 큰 첫번째 3의 배수 : %d", num, i);
                    break out;
                }
            }




        



    }//main
}
