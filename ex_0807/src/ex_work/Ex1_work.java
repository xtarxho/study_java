package ex_work;

import java.util.Scanner;

public class Ex1_work {
    public static void main(String[] args) {
        
        //주말 숙제

        //윤년구하기
        //키보드에서 년도를 입력받는다
        //입력받은 년도가 윤년인지 평년인지 판단
        //---윤년---
        //4로 나눠떨어지지만 100으로는 나눠떨어지지 않는 년도
        //연도가 100으로 나눠떨어지는 해는 평년
        // -> 4년 주기에 포함이 되어 있더라도 100으로 나눠지면 평년
        //단, 100으로 나눠지더라도 400으로도 나눠지면 윤년

        //-------------------------
        //연도 : 2020
        //2020년은 윤년입니다

        //연도 : 2021
        //2021년은 평년입니다

        Scanner sc = new Scanner(System.in);

        System.out.print( "연도 : ");
        int year = sc.nextInt();
        boolean res = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
        if(res == true){ // if(!res) -> if(res == false) 이런 형식으로 줄여서 사용 가능
            System.out.println(year + "년은 윤년입니다");
        }else{
            System.err.println(year + "년은 평년입니다");
        }













    }//main
}
