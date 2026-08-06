package ex2_valueType;

public class Ex1_valueType {
    public static void main(String[] args) {
        
       /*
        자바의 기본 자료형
        논리형 : boolean....1bit
        문자형 : char......2byte
        정수형 : byte .....1byte -> -128 ~ 127
                short ....2byte -> -32768 ~ 32767
                int ......4byte -> -21억 ~ 21억
                long .....8byte -> -922경 ~ 922경
        실수형 : float ....4.x byte
                double ....8.x byte


        변수 : 데이터를 저장하는 공간
        자료형 변수명;(선언)
        변수명 = 값; (대입)

        자료형 변수명 = 값;(초기화)

        변수선언 규칙
        1. 숫자로 시작할 수 없다.
        2. _를 제외하고 특수문자를 사용할 수 없다(공백도 X)
        3. 한글로 변수이름 짓지 않는다
        4. 반드시 소문자 알파벳으로 시작하도록 한다
      */

    /*
        1. 카멜표기법(Camel Case)
            - 변수명이나 메소드명에서 주로 사용
            - 첫 단어는 소문자로 시작하고, 두 번째 단어부터는 첫 글자를 대문자로 작성
            - 예시: myVariableName, studentScore

        2. 파스칼 표기법(Pascal Case)
            - 클래스명 등에서 주로 사용
            - 모든 단어의 첫 글자를 대문자로 작성
            - 예시: MyClassName, StudentScore

        3. 헝가리안 표기법(Hungarian Notation)
            - 변수명 앞에 데이터 타입 또는 목적을 접두어로 붙이는 방식
            - 예시: strName, iCount, bFlag (str: 문자열, i: 정수, b: 불리언 등)

        4. 스네이크 표기법(Snake Case)
            - 단어 사이를 언더스코어(_)로 구분
            - 주로 상수명이나 일부 언어나 환경에서 변수명에 사용
            - 예시: student_score, total_amount
    */

            //논리형 : 참과 거짓(true, false)의 두 가지 값만을 
            //저장할 수 있는 자료형
            boolean b1; //선언
            b1 = true; //대입
            b1 = false;
            //b1=10; <-- 자료형 타입이 올바르지 않기 때문에 오류발생
            System.out.println("b1 : " + b1);

            //정수형 : 소수점이 없는 정수 값만 저장하는 자료형
            byte b =127;
            short s = 32767;
            int n = 5000;
            long lo = 2200000000L;

            System.out.println("b : "+ b);
            System.out.println("s : "+ s);
            System.out.println("n : "+ n);
            System.out.println("lo : "+ lo);
    }
}
