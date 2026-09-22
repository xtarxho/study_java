package ex_0814;

import java.util.Scanner;

public class Ex3_Work {
    public static void main(String[] args) {

        // 키보드에서 아무값이나 입력받는다
        // 입력받은 문자열에 소문자 a의 갯수를 출력
        // ---------------------------
        // 입력 : wefwefwxcxaweagd
        // a의 갯수 : 
        
        Scanner sc = new Scanner(System.in);

        System.out.print("입력 : ");
        String str = sc.next();

        int cnt = 0;
        for( int i = 0; i < str.length(); i++ ){

            if (str.charAt(i) == 'a') {
                cnt++;
            }

        }
        System.out.print("a의 갯수 : " + cnt);
    }// main
}
