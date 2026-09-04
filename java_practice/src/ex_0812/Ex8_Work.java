package ex_0812;

import java.util.Random;

public class Ex8_Work {
    public static void main(String[] args) {

        // 0812 숙제
        // 변수 money에 10 ~ 5000 사이의 난수를 발생시킨다
        // 단 발생한 난수 money는 1의 자리가 반드시 0이 되도록 한다
        // money를 최소한의 동전으로 몇 개씩 거슬러 지는지 출력
        // ----------------------------------
        // 금액 : 2590
        // 500원 : 5
        // 50원 : 1
        // 10원 : 4
        int[] coin = {500, 100, 50, 10};

        Random rnd = new Random();

        int money = rnd.nextInt(500) + 1;
        money *= 10;

        System.out.print("금액 : " );
        System.out.println(money);

        for( int i = 0; i < coin.length; i++ ){

            int res = money / coin[i];

            if (res > 0) {
                System.out.printf("%d원 : %d\n",coin[i],res);
                money %= coin[i];
            }

        }

    }// main
}
