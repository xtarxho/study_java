package ex_work;

import java.util.Scanner;

public class Ex2_work {
    public static void main(String[] args) {

        //이메일 : abc@korea.com
        //이메일 형식 오류  -> 이메일 길이가 짧으면 나오게 하기
        
        //이메일 : aabbcc@korea.com
        //aabbcc님 환영합니다

        Scanner sc =new Scanner(System.in);
        System.out.print( "이메일 : " );
        String email = sc.next();

        //@를 기준으로 이메일 주소를 분할해서 sp배열에 담는다
        email.split("@"); //email을 분할 시켜주는 역할을 한다 예) aabbcc ->배열[0]  @   korea.com  -> 배열[1]
        String[] sp = email.split("@");    // sp[0] -> aabbcc  sp[1] -> korea.com

        String res = sp[0];

        if ( res.length() < 4 || res.length() > 8 ) {
            System.out.println("이메일 형식이 올바르지 않음");
        }else{
            System.out.println(res + "님 환영합니다");
        }

        
    }//main

}
