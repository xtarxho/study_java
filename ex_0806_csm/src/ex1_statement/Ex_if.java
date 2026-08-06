package ex1_statement;

public class Ex_if {
    public static void main(String[] args) {
        
        //제어문 - 프로그램의 흐름을 제어하는 문장
        //분기문과 반복문으로 나뉜다
        //분기문 : if, switch
        //반복문 : for, while, do-while

        //if문
        //if(조건식){
        //  조건식이 참일 떄 실행되는 영역
        //}

        int n = 50;

        //String은 쌍따옴표 안에 여러글자(문자열)를 저장하기 위한 자료형 
        String str = "안녕하세요"; //String 알아보기
        
        if( n != 50 ){
            str = ("n은 50입니다");
        }
        System.out.println(str);


    }
}
