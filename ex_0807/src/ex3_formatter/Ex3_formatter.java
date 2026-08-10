package ex3_formatter;

public class Ex3_formatter {
    public static void main(String[] args) {
        
        //저의 나이는 20살 입니다 -> 출력하고싶은 것
        int age = 20;
        System.out.println( "저의 나이는 " + age + "살 입니다" ); // -> 지금까지 배운것 
        System.out.printf(
             "저의 나이는 %d살 입니다\n", age); //새로 배우는 것
        



            //저는 20살이고 3층에 살아요
        System.out.printf(
                    "저는 %d살이고 %02d층에 살아요\n", 20, 3);


        //나는 박씨야 
        System.out.printf( "나는 %c씨야\n", '박');



        //나는 a형이야
        System.err.printf( "나는 %c형이야\n", 'a'  );



        //원주율은 3.141592
        System.out.printf( "원주율 : %.2f\n", 3.141592 );


        //저는 홍길동 입니다
        System.out.printf( "저는 %s 입니다\n", "홍길동");

        
        System.out.printf( "%s\t %d\t %d\n", "김윤", 100, 95);
        System.out.printf( "%s\t %d\t %d\n", "김길동", 100, 7);

        
        //오늘 강수량은 15%입니다
        System.out.printf( "오늘 강수량은 %d%%입니다\n", 15 ); 




    }//main

    /*
    
    formatter의 문법
    \n : 강제개행 (줄바꿈)
    \t : 결과 라인 맞추기

    %d : 정수 타입 들어가야함
    %c : 문자 타입  ->  char
    %f : 실수 타입 (float, double)
    %.2f : -> 소수점 줄이기
    %s : 문자열 타입 (String)
    %% : % 특수문자    
    02 : 한자리 숫자를 0을 붙여서 2자리로 만들기
    */





}
