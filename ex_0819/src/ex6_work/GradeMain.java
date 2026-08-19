package ex6_work;

import java.util.Scanner;

public class GradeMain {
    public static void main(String[] args) {
    
        //--등급기준--
        //VIP -> 구매금액의 10%
        //GOLD -> 5%
        //SLIVER -> 3%

        //키보드에서 구매금액과 등급을 입력받고 적립포인트를 계산해보기
        //---------------------
        //구매금액 : 10000
        //등급 : VIP
        //적립포인트 : 1000

        //구매금액 : 1000
        //등급 : aaa -> 없는 등급을 받았을 경우
        //회원등급이 올바르지 않습니다
        //적립포인트 : 0

        Scanner sc = new Scanner(System.in);
        System.err.print( "구매금액 : " );
        int n = sc.nextInt();

        System.out.print( "등급 : " );
        String grade = sc.next();

        GradeSub gs = new GradeSub();
        int res = gs.myPoint(grade, n);
        System.out.println("적립포인트 : " + res);
        
    }//main

}
