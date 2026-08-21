package ex8_work;

import java.util.Random;

public class WorkMain {
    public static void main(String[] args) {
        
        // 0829에도 같은 문제 나올예정

        // 변수 money에 10 ~ 5000 사이의 난수를 발생시킨다
        // ( 단, 발생한 난수 money는 1의 자리가 반드시 0이 되도록 한다 )
        // money가 최소한의 동전으로 몇 개씩 거슬러 지는지 출력
        // -------------------------
        // 금액 : 2590
        // 500원 : 5
        // 50원 : 1
        // 10원 : 4
        int[] coin = { 500, 100, 50, 10 };

        int money = new Random().nextInt(500) + 1;
        money *= 10;

        System.out.println("금액 : " + money);
        WorkSub ws = new WorkSub();
        ws.myCoin(coin, money);

    }// main
}
