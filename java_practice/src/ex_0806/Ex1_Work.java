package ex_0806;

import java.util.Scanner;

public class Ex1_Work {
    public static void main(String[] args) {
        
         // [문제1] 시험 점수가 60점 이상이거나, 과제 점수가 80점 이상이면 "합격"을 출력하고,
        // 둘 다 해당되지 않으면 "불합격"을 출력하는 코드를 작성해보세요.

        Scanner sc = new Scanner(System.in);

        System.out.print("시험 점수 : ");
        int n1 = sc.nextInt();

        System.out.print("과제 점수 : ");
        int n2 = sc.nextInt();

        if ( n1 >= 60 || n2 >= 80 ) {
            System.out.println("합격");
        }else{
            System.out.println("불합격");
        }

    }//main
}
