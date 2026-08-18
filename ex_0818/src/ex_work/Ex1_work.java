package ex_work;

import java.util.Scanner;

public class Ex1_work {
    public static void main(String[] args) {
        
        //0818 숙제
        //키보드에서 입력받은 값이 회문인지 판단 -> 앞에서 읽는거랑 뒤에서 읽는거랑 같은 것(회문)
        //----------------------------
        //입력 : abcba
        //abcba은(는) 회문입니다

        //입력 : abc
        //abc은(는) 회문이 아닙니다

        Scanner sc = new Scanner(System.in);
        System.out.print( "입력 : " );
        String str = sc.next();

        
        for( int i = 0; i < str.length(); i++ ){
                if(str.charAt(i) == str.charAt(str.length() - 1 - i )){
                    
                    
                }else{
                    
                    System.out.println(str + "은(는) 회문이 아닙니다");
                }
                System.out.println(str + "은(는) 회문입니다");
                
                
                
            }

    }//main
}
