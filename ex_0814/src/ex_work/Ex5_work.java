package ex_work;

import java.util.Scanner;

public class Ex5_work {
    public static void main(String[] args) {
        
        //문장 : HelloWorld
        //홀수에 위치해 있는 알파벳만 출력하기
        //결과 : Hlool

        // Scanner sc = new Scanner(System.in);
        // System.out.print( "문장 : " );
        // String word = sc.next();

        // String h = "";
        // for( int i = 0; i < word.length(); i++ ){
        //     if (i % 2 == 1) {
        //         h += i; //틀림
        //     }

        // }
        // System.out.print( "결과 : " + (String)h );

//-----------------------------------------------------------
        //쌤이 알려준 방법
        Scanner sc = new Scanner(System.in);
        System.out.print( "문장 : " );
        String in = sc.next();

        String res = "";
        for( int i = 0; i < in.length(); i+=2 ){

            res += in.charAt(i); //이해하기 


        }
        System.out.println(res);

    }//main
}
