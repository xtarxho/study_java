package ex_0807;

import java.util.Scanner;

public class Ex5_Work {
    public static void main(String[] args) {

        //풀긴 풀었는데 수정하면서 풀음 다시 풀어보기 + 공책에 적기

        // 정수n1, n2를 입력받고
        // n1 ~ n2사이의 합을 출력
        // ---------------------
        // 수1 : 2
        // 수2 : 5
        // 결과 : 14

        // 수1 : 5
        // 수2 : 2
        // 결과 : 14

        Scanner sc = new Scanner(System.in);

        System.out.print("수1 : ");
        int su1 = sc.nextInt();

        System.out.print("수2 : ");
        int su2 = sc.nextInt();

        int sum = 0;
        int tmp = 0;

        if (su1 > su2) {
            tmp = su1;
            su1 = su2;
            su2 = tmp;
        }
        
        for (int i = su1; i <= su2; i++ ) {
            sum += i;

        }
        System.out.println(sum);

    }// main
}
