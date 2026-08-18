package ex_work;

import java.util.Scanner;

public class Ex6_work {
    public static void main(String[] args) {
        
        //문장 : aabbcc
        //결과 : abc ->중복된 값말고 하나씩만 나오게 하기
        //res.indexOF()를 사용해야 한다

        Scanner sc = new Scanner(System.in);
        System.out.print( "문장 : " );
        
        String in = sc.next();
        String res = "";

        //못풀었음
        for( int i = 0; i < in.length(); i++ ){
            
            char curr = in.charAt(i);

            if (res.indexOf(curr) == -1) { //res한테 curr에 해당하는 값이 없으면 -1이된다 
                res += curr;                   //if문이 가장 핵심 부분 
            }

        }//for
        
        System.out.println("결과 : " + res);

    }//main
}
