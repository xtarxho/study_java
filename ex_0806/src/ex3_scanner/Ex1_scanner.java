package ex3_scanner;

import java.util.Scanner;


public class Ex1_scanner {
public static void main(String[] args) {
    
    // 키보드에서 값을 받기위한 scanner객체
    Scanner sc = new Scanner(System.in);
        
    System.out.print("정수 : ");
    int n = sc.nextInt();
    System.out.println("값 : " + n);

    System.out.println("문자열 : ");
    String s = sc.next();
    System.out.println("값 : " + s);



    }// main
}
