package ex10_work;

import java.util.Scanner;

public class Ex2_work {
    public static void main(String[] args) {

        // 정수 : 100
        // 결과 : 100

        // 정수 : abc10
        // abc10은(는) 정수가 아닙니다

        Scanner sc = new Scanner(System.in);
        System.out.print("정수 : ");
        
        String str = "";

        try {
            
            str = sc.next();
            int n = Integer.parseInt(str); //문자열을 진짜 정수로 바꿔주는 
            System.out.println("결과 : " + n);

        } catch (Exception e) {
            System.out.println(str + "은 정수가 아님");

        }
    }// main
}
