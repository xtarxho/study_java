package ex2_random;

import java.util.Random;

public class Ex1_random {
    public static void main(String[] args) {

        // 216 ~ 1378 사이의 난수 
        //new Random().nextInt( 난수의 범위 ) + 시작수;
        //new Random().nextInt( 큰수 - 작은수 + 1 ) + 시작수;
        //int num = new Random().nextInt(7) + 7;

        //int num = new Random().nextInt(1378 - 216 + 1) + 216;
        //System.out.println(num);


        // 2 ~ 9 사이의 난수에 해당하는 구구단

        //내가 푼 방법 - 틀림
        int num1 = new Random().nextInt(9 - 2 + 1 );
        int num2 = new Random().nextInt( 9 );
        System.out.println( num1 + "*" + num2 + "=" + num1 * num2 );


        //쌤이 알려주신 방법
        int dan = new Random().nextInt(9 - 2 + 1 ) + 2;        

        for( int i = 1; i <= 9; i++){
            System.out.println(
                dan + "*" + i + "=" + dan*i
            );
        }//for



    }//main
}
