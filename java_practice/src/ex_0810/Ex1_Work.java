package ex_0810;

import java.util.Scanner;

public class Ex1_Work {
    public static void main(String[] args) {
        
        //1부터 100까지의 숫자중에서
        //키보드에서 입력받은 수 보다 큰 첫번째
        //3의 배수를 찾아 출력
        //-----------------------
        //입력 : 55
        //55보다 큰 첫번째 3의 배수 : 57

        Scanner sc = new Scanner(System.in);

        System.out.print( "입력" );
        int num = sc.nextInt();

        int i = 1;
        for( ; i <= 100; i++ ){

            
                if (num < i && i % 3 == 0) {
                    break;
                }
 
            }
            System.out.println(i);

    }//main
}
