package ex_0814;

import java.util.Scanner;

public class Ex2_Work {
    public static void main(String[] args) {
        
        //이메일 : abc@korea.com
        //이메일 형식 오류  -> 이메일 길이가 짧으면 나오게 하기
        
        //이메일 : aabbcc@korea.com
        //aabbcc님 환영합니다

        Scanner sc = new Scanner(System.in);

        System.out.print("이메일 : ");
        String email = sc.next();

        String[] id = email.split("@");

        if (id[0].length() < 3 || id[0].length() > 8) {
            System.out.println("이메일 형식 오류");
        }else{
            System.out.println(id[0] + "님 환영합니다");
        }

    }//main
}
