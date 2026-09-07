package ex_0812;

import java.util.Random;

public class Ex9_Work {
    public static void main(String[] args) {

        // 0812 숙제
        // 1에서 45사이의 중복되지 않는 난수 6개를 출력하는
        // 로또번호 생성기 만들기
        // -------------------
        // 17 2 45 27 9 11

        int[] lotto = new int[6];

        Random rnd = new Random();

        out : for( int i = 0; i < lotto.length;  ){

            lotto[i] = rnd.nextInt(45) + 1;

            for( int j = 0; j < i; j++ ){

                if (lotto[i] == lotto[j]) {
                    continue out;
                }

            }

            System.out.print(lotto[i] + " ");
            i++;
        }

    }// main
}
