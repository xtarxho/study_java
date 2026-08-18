package ex_work;

import java.util.Scanner;

public class Ex4_work {
    public static void main(String[] args) {
        
        //키보드에서 입력받은 OX값을 계산하여 출력하기
        //-----------------------------------
        //입력 : ooxxo
        //결과 : 4

        Scanner sc = new Scanner(System.in);
        System.out.print( "입력 : " );
        String str = sc.next();

        int cnt = 0;
        int sum = 0; //-> 쌤이 알려줌. 최종결과를 알기 위한 변수
        for( int i = 0; i < str.length(); i++){
            if ( 'o' == str.charAt(i)) {
                cnt++;
                
            }else{
                cnt = 0; //cnt를 0으로 초기화하는 것 쌤이 알려줌
            }
            sum += cnt; //쌤이 알려줌
        }
        System.out.printf( "결과 : %d", sum);
    }//main
}
//oxooxo