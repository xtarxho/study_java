package ex1_string;

import java.util.Scanner;

public class Ex1_String {
    public static void main(String[] args) {
        
        //String클래스의 두 가지 특징
        //1) 객체 생성방법이 2가지( 암시적 생성방법, 명시적 생성방법 )
        //2) 한 번 생성된 문자열의 내용은 변하지 않는다


        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");


        //==은 객체간 비교에서는 주소값 비교를 한다
        if(s1==s2){
            System.out.println("주소가 같다");
        }else{
            System.out.println("주소가 다르다");
        }

        //String클래스의 불변의 법칙
        String greet = "안녕";
        greet += "하세요";
        System.out.println(greet);


        Scanner sc = new Scanner(System.in);
        System.out.print( "연산자 : " );
        String op = sc.next();

        if (op.equals( "+")) {
            System.out.println( "+연산 할게요");
        }

    }//main
}
