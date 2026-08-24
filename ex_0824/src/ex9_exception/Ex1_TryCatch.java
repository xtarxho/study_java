package ex9_exception;

import java.util.Scanner;

public class Ex1_TryCatch {
    public static void main(String[] args) {

        // 예외처리(try-catch)
        // 자바에서 프로그램이 실행되는 도중 예외(오류,버그)가 발생하면
        // 그 시점에서 프로그램이 강제적으로 종료가 된다.
        // 이것은 올바른 판단이지만 때로는 예상할 수 있는 예외가 발생했을 때
        // 프로그램을 강제 종료하지 않고 이후의 작업을 진행하고자 할 때가 있다.
        // 예외처리를 통해 프로그램의 비정상적인 종료를 줄여보자
        Scanner sc = new Scanner(System.in);
        System.out.print("수1 : ");
        int su1 = sc.nextInt();

        System.out.print("수2 : ");
        int su2 = sc.nextInt();

        int result = 0;
        int[] arr = { 1, 2, 3 };

        try {
            result = su1 / su2;
            arr[3] = 10;

        } catch (ArithmeticException e) {

            System.out.println("정수는 0으로 나눌 수 없습니다");

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("존재하지 않는 index로 접근불가");

        }

        System.out.println("결과 : " + result);

    }// main
}
