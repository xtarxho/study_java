package ex_work;

import java.util.Scanner;

public class Ex4_work {
    public static void main(String[] args) {
        
        //문제 -> 정답을 적어주고 정답처럼 나오게 코드 작성하기
        //수1 : 10
        //수2 : 20
        //연산자 : +
        //10 + 20 = 30

        Scanner sc = new Scanner(System.in);
        
        System.out.print( "수1 : " );
        int a = sc.nextInt();

        System.out.print( "수2 : " ); 
        int b = sc.nextInt();

        System.out.print( "연산자 : " );
        String c = sc.next();

        switch( c ){ //비교값

            case "+": //조건값
                System.out.println(a + "+" + b + "=" + (a+b));
                break;
            case "-": //조건값
                System.out.println(a + "-" + b + "=" + (a-b));
                break;
            case "*": //조건값
                System.out.println(a + "*" + b + "=" + (a*b));
                break;
            case "/": //조건값
                System.out.println(a + "/" + b + "=" + ((float)a/b));  // -> 나누기 소수점 만들려면 둘중에 한개는 float형태로 있어야한다
                break;

            default:
                System.out.println("연산자 오류");
                break;


        }//switch



        // 다음 문제를 풀어보세요. 코드로 입력과 출력을 구현하세요. (정답은 제공하지 않습니다)
        //--------------
        // 1)
        // 수1 : 15
        // 수2 : 3
        // 연산자 : -
        // 15 - 3 = 

        
        
        
        
       
       
        //--------------
        // 2)
        // 수1 : 7
        // 수2 : 8
        // 연산자 : *
        // 7 * 8 = 

       
       
       
        
        
        //--------------
        // 3)
        // 수1 : 20
        // 수2 : 4
        // 연산자 : /
        // 20 / 4 =












    }//main
}
