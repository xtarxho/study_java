package ex10_work;

import java.util.Scanner;

public class Ex1_Work {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("정수 : ");

        try {
            int num = sc.nextInt();
            System.out.print("입력받은 수 : " + num);

        } catch (Exception e) {
            System.out.println("정수만 입력할 수 있습니다.");
        }

    }// main
}