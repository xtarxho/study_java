package ex8_work;

public class WorkSub {

    public void myCoin(int[] coin, int money) {

        for (int i = 0; i < coin.length; i++) {
            int res = money / coin[i];
            if (res > 0) {
                System.out.printf("%d원 : %d개\n", coin[i], res);
                money %= coin[i];
            }
        }

    }// myCoin()

}
