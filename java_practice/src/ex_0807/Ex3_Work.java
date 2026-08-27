package ex_0807;

import java.util.Random;

public class Ex3_Work {
    public static void main(String[] args) {
        
        // A ~ Z사이의 값중 하나를 랜덤으로 출력

        int num = new Random().nextInt('Z' - 'A' + 1) + 'A';
        System.out.println((char)num);

    }//main
}
