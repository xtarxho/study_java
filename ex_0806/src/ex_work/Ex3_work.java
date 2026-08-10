package ex_work;

import java.util.Scanner;

public class Ex3_work {
    public static void main(String[] args) {

        // 키보드에서 나이와 키를 입력을 받는다
        // 나이가 15세 이상이거나 키가 150cm이상이면 탑승가능
        // 그렇지 않으면 탑승불가를 출력

        // 나이 : 14
        // 키 : 140
        // 탑승불가

        Scanner sc = new Scanner(System.in);

        System.out.print("나이 : ");
        int age = sc.nextInt();

        System.out.print("키 : ");
        int cm = sc.nextInt();

        if (age >= 15 || cm >= 150) {
            System.out.println("탑승가능");
        } else {
            System.out.println("탑승불가");
        }









        // [응용문제1] 
        // 키보드에서 국어, 영어 점수를 입력받는다.
        // 두 과목 중 한 과목이라도 90점 이상이면 "우수"를 출력, 아니면 "일반"을 출력한다.

        Scanner st = new Scanner(System.in);
        
        System.out.print("국어 : " );
        int language = st.nextInt();

        System.out.print("영어 : ");
        int english = st.nextInt();

        if( language >= 90 || english >= 90 ){
            System.out.println("우수" );
        }else{
            System.out.println( "일반");
        }


        // [응용문제2]
        // 키보드에서 나이와 회원가입 여부(예: Y/N)를 입력받는다.
        // 나이가 19세 이상이거나 회원가입이 되어 있으면 "입장 가능", 아니면 "입장 불가"를 출력한다.


        Scanner ar = new Scanner(System.in);

        System.out.print( "나이 : " );
        int age1 = ar.nextInt();

        System.out.print( "회원가입 여부 : " );

        






        // [응용문제3]
        // 키보드에서 퀴즈 점수와 출석일수를 입력받는다.
        // 퀴즈 점수가 80점 이상이거나 출석일수가 10일 이상이면 "합격", 아니면 "불합격"을 출력한다.





    }
}
