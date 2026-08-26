package ex_0806;

import java.util.Scanner;

public class Ex3_Work {
    public static void main(String[] args) {
        
        // [응용문제2]
        // 키보드에서 나이와 회원가입 여부(예: Y/N)를 입력받는다.
        // 나이가 19세 이상이거나 회원가입이 되어 있으면 "입장 가능", 아니면 "입장 불가"를 출력한다.

        Scanner sc = new Scanner(System.in);
        System.out.print( "나이 : " );
        int age = sc.nextInt();

        System.out.print("회원가입 여부 : ");
        String in = sc.next();

        // 회원가입 여부에서 == 사용하면 왜 안되는건지???
        if (age >= 19 || in.equalsIgnoreCase("Y")) {
            System.out.println("입장 가능");
        }else{
            System.out.println("입장 불가");
        }
    }//main
}
