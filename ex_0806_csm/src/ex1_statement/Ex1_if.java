package ex1_statement;

public class Ex1_if {
    public static void main(String[] args) {
        
        //제어문 - 프로그램의 흐름을 제어하는 문장
        //분기문과 반복문으로 나뉜다
        //분기문 : if, switch
        //반복문 : for, while, do-while

        //if문
        //if(조건식){
        //  조건식이 참일 떄 실행되는 영역
        //}

        int n = 49; // 변수 n에 49를 저장

        // String은 문자 여러 개(문자열)를 저장하는 자료형
        String str = ""; // str 변수 선언 및 빈 문자열로 초기화
        // null은 아무것도 없는 상태이고, ""(빈 문자열)은 값을 넣을 수 있는 상태

        // n이 50과 같다면 str에 "n은 50입니다" 저장
        if( n == 50 ){
            str = "n은 50입니다";
        }

        // n이 50과 같지 않다면 str에 "n은 50이 아닙니다" 저장
        if( n != 50 ){
            str = ("n은 50이 아닙니다");
        }

        System.out.println(str); // str 값을 출력


    }
}
