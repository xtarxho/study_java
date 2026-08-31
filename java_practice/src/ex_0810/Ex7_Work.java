package ex_0810;

import java.util.Scanner;

public class Ex7_Work {
    public static void main(String[] args) {

        // 1부터 입력받은 숫자까지 값을 순차적으로 출력하되
        // 5의 배수이거나, 1의자리가 3인 경우는 출력에서 제외하기
        // 입력 : 15
        // 1
        // 2
        // 4
        // 6
        // 7
        // 8
        // 9
        // 10
        // 11
        // 12
        // 14

        Scanner sc = new Scanner(System.in);

        System.out.print("입력 : ");
        int n = sc.nextInt();

        for( int i = 1; i <= n; i++ ){
            if (i % 5 == 0 || i % 10 == 3) {
                continue;
            }
            System.out.println(i);
        }
        

    }// main
}
