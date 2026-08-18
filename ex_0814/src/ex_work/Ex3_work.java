package ex_work;

import java.util.Scanner;

public class Ex3_work {
    public static void main(String[] args) {
    
        //키보드에서 아무값이나 입력받는다
        //입력받은 문자열에 소문자 a의 갯수를 출력
        //---------------------------
        //입력 : wefwefwxcxaweagd
        //a의 갯수 : 2


        //내가 푼거 틀림
        // Scanner sc = new Scanner(System.in);
        // System.out.print( "입력 : " );
        // String n = sc.next();

        // int cnt = 0;
        // for( int i = 0; i <= n.length(); i++ ){
        //     for( int j = 0; j <= n.length(); j++ ){
        //         if ( i == j ) {
        //             cnt ++;
                    
        //         }
        //     }
        //     break;
        // }
        // System.out.println("a의 갯수 : " + cnt);


        //쌤이 알려주신 방법
        Scanner sc = new Scanner(System.in);
        int cnt = 0;

        System.out.print( "입력 : " );
        String str = sc.next();

        for( int i = 0; i < str.length(); i++ ){

            if ( str.charAt(i) == 'a' ) {
                cnt++;
            }
            
        }
        System.out.println("a의 갯수 : " + cnt);








    }//main

}
