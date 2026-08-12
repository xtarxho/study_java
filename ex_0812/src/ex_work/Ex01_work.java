package ex_work;

import java.util.Scanner;

public class Ex01_work {
    public static void main(String[] args) {

        // 입력 : 5 -> 키보드가 5를 받으면 5개 index를 만들어야함
        // ABCDE

        Scanner sc = new Scanner(System.in);
        System.out.print("입력 : ");
        int n = sc.nextInt();

        char[] ch = new char[n];

        for (int i = 0; i < ch.length; i++) {
            ch[i] = (char)('A' + i);
            System.out.print(ch[i]);

        }
        System.out.println();
        System.out.println("------------------------");
        
        char[] cArr = new char[n];
        char ch1 = 'A';

        for( int i = 0; i < n; i++ ){
            
            System.out.print( cArr[i] = ch1++ );
        }







    }// main

}
