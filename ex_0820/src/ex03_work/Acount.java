package ex03_work;

import java.util.Scanner;

public class Acount {
    public static void main(String[] args) { // void -> 반환형이 없는 메소드 하지만 return만 사용하는건 가능하다

        /*
         * 1.입 금
         * 2.출 금
         * 3.잔액확인
         * ext.종 료
         * >> 1
         * ---입 금---
         * 입금액 : 1000 ->키보드에서 받음
         * 입금성공
         * ---------------------
         * 1.입 금
         * 2.출 금
         * 3.잔액확인
         * ext.종 료
         * >> 3
         * ---잔액확인---
         * 1000원
         * -------------
         * 1.입 금
         * 2.출 금
         * 3.잔액확인
         * ext.종 료
         * >> 4 ->1,2,3 이외의 모든 값을 치면 종료
         * atm 사용을 종료합니다
         */
        // 쌤이 알려주신 방법
        Scanner sc = new Scanner(System.in);

        Atm atm = new Atm();

        while (true) {

            System.out.println("1.입 금");
            System.out.println("2.출 금");
            System.out.println("3.잔액확인");
            System.out.println("ext.종 료");
            System.out.print(">> ");

            int select = sc.nextInt();
            int money = 0;

            switch (select) {
                case 1:
                    System.out.println("---입금---");
                    System.out.print("입금액 : ");
                    money = sc.nextInt();
                    atm.deposit(money); // deposit에 money를 넣어준다
                    break;
                case 2:
                    System.out.println("---출금---");
                    System.out.print("출금액 : ");
                    money = sc.nextInt();
                    atm.withdraw(money); // 출금할때 사용한다 ATM클래스에서 가져오는 방법
                    break;
                case 3:
                    System.out.println("잔액확인");
                    atm.balance();
                    break;

                default:
                    System.out.println("atm 사용을 종료합니다");
                    return;
            }// swtich

            System.out.println("---------------------");

        }

        // 내가 풀어본거
        // Scanner sc = new Scanner(System.in);

        // String str = "";
        // int money = 0;

        // do{
        // System.out.println("1.입 금");
        // System.out.println("2.출 금");
        // System.out.println("3.잔액확인");
        // System.out.println("ext.종 료");
        // str = sc.next();

        // switch (str) {
        // case "1":
        // System.out.print("입금액 : ");
        // money = sc.nextInt();
        // System.out.println("입금성공");
        // break;

        // default:
        // break;
        // }

        // }while(str.equals(""){
        // System.out.println();
        // }
    }// main

}
