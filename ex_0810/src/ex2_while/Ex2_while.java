package ex2_while;

import java.util.Scanner;

public class Ex2_while {
    public static void main(String[] args) {

        // 키보드에서 3을 입력받을 때까지 영원히 반복하는 while
        // -------------------
        // 입력 : 1
        // 값 : 1
        // 입력 : 2
        // 값 : 2
        // 입력 : 3
        // 값 : 3
        // 종료됨
        Scanner sc = new Scanner(System.in);
        
        
        int num = 0;

        while (num != 3) {
            System.out.print("입력 : ");
            num = sc.nextInt();
            System.out.println("값 : " + num);

        }
        System.out.println("종료됨");

    }// main
}
