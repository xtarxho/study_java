package ex03_work;

public class Atm {

    private int money; // 현재 가지고 있는 잔액

    // 입금을 위한 기능이 있어야한다
    public void deposit(int money) {
        System.out.println("입금성공");
        this.money += money; // this.money는 private 머니이다. 누적해서 담아준다
    }

    // 출금을 위한 기능도 있어야한다
    public void withdraw(int money) {
        if (this.money - money < 0) {
            System.out.println("잔액부족");
        } else {
            System.out.println("출금성공");
            this.money -= money;
        }
    }

    // 잔액확인을 위한 기능도 있어야한다
    public void balance() {
        System.out.println("잔액 : " + money);
    }

}
