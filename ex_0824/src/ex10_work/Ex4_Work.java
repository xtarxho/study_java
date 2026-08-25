package ex10_work;

import java.util.Scanner;

public class Ex4_Work {
    public static void main(String[] args) {

        // 입력 : 1771
        // 1771은(는) 4자리의 홀수입니다

        // 입력 : 30
        // 30은(는) 2자리의 짝수입니다

        // 입력 : 100a
        // 100a은(는) 정수가 아닙니다

        Scanner sc = new Scanner(System.in);
        System.out.print("입력 : ");

        String str = sc.next();

        try {
            int n = Integer.parseInt(str);
            System.out.printf("%d는 %d자리의 ", n, str.trim().length()); // 공백 제거한 후의 길이 -> trim().length()
            if (n % 2 == 0) {
                System.out.println("짝수입니다");
            } else {
                System.out.println("홀수입니다");
            }

        } catch (Exception e) {
            System.out.println(str + "는(은) 정수가 아닙니다");
        }

    }// main

}
