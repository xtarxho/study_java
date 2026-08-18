package ex_work;

import java.util.Scanner;

public class Ex7_work {
    public static void main(String[] args) {
        
        //키보드에서 아무값이나 입력받고
        //숫자인지 아닌지를 판단하기
        //-------------------------
        //값 : 123
        //123은 숫자입니까? true

        //값 : a12
        //a12은(는) 숫자입니까? false

        Scanner sc = new Scanner(System.in);
        System.out.print("값 : ");
        String str = sc.next();

        int i = 0;  //전역변수로 사용하고싶어서 for문 밖에 만들었다 -> 제일 중요한 코드 
        for( ; i < str.length(); i++ ){
            char ch = str.charAt(i);

            if ( ch < '0' || ch > '9' ) {
                break;
            }
        }
        if ( i == str.length() ) {  
            System.out.println(str + "은 숫자임");
        }else{
            System.out.println(str + "은 숫자 아님");
        }


    }//main
}
