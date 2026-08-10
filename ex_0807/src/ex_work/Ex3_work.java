package ex_work;

import java.util.Random;

public class Ex3_work {
    public static void main(String[] args) {
        
        // A ~ Z사이의 값중 하나를 랜덤으로 출력
        
        //내가 푼 방법
        int alpabat = new Random().nextInt( 90 - 65 + 1 ) + 65;
        System.out.println( (char)alpabat );


        //쌤이 알려주신 방법
        int rnd = new Random().nextInt( 'Z' - 'A' + 1 ) + 'A';
        System.out.println( (char)rnd );
    }
}
