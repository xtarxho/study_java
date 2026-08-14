package ex1_string;

public class Ex2_String {
    public static void main(String[] args) {
        
        String str = "Hong Gil Dong";
        int index = str.length();
        System.out.println("str의 길이 : " + index);


        index = str.indexOf('o');
        System.out.println("맨 처음 문자 o의 위치 : " + index);

        index = str.indexOf("Gil");
        System.out.println("문자열 Gil의 위치: " + index);

        index = str.lastIndexOf('o');
        System.out.println("마지막 문자 o의 위치 : " + index);

        char res = str.charAt(6);
        System.out.println("6번째 문자 : " + res);

        String ss = str.substring(1, 6);
        System.out.println("잘라낸 문장 : " + ss);

        String apple = "apple";
        //equals는 대소문자까지 동일해야만 같은 값으로 인정한다
        if ( apple.equals("apple") ) {
            System.out.println( "사과");            
        }

        if (apple.equalsIgnoreCase("Apple")) {
            System.out.println("대소문자 상관없이 사과");
        }

        String password = " 1234";
        //trim()을 통해 문자열 앞 뒤의 의미없는 공백을 제거할 수 있음
        String pwd2 = password.trim();
        System.out.println(pwd2 + "의 길이 : " + pwd2.length());

        //문자열로 작성된 숫자형태의 데이터를 실제 숫자로 바꿔주는 메서드
        String number = "100";
        int num = Integer.parseInt(number);                //String은 정수타입한테 값을 주지 못함 
        System.out.println( num + 1 );

        //int라는 자료형을 클래스 형태로 사용하고 싶을때 Integer를 사용한다
        // -> 부모개념의 클래스 객체 (Wrapper)
        //기본 자료형의 Wrapper클래스
        //boolean의 Wrapper -> Boolean
        //char의 Wrapper -> Character
        //byte의 Wrapper -> Byte
        //short의 Wrapper -> Short
        //int의 Wrapper -> Integer
        //long의 Wrapper -> Long
        //float의 Wrapper -> Float
        //double의 Wrapper -> Double 

    }//main
}
