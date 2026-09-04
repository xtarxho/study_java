package ex_0812;

import java.util.Scanner;

public class Ex5_Work {
    public static void main(String[] args) {

        // 학생 수를 입력하세요 : 3
        // 학생 1의 성적: 90
        // 학생 2의 성적: 73
        // 학생 3의 성적: 84
        // -----------------
        // 평균 성적: 82.333336
        // ---평균 이상인 학생들---
        // => 학생1: 90
        // => 학생3: 84
        // 평균 이상인 학생 수: 2

        Scanner sc = new Scanner(System.in);

        System.out.print("학생 수 : ");
        int size = sc.nextInt();

        int[] arr = new int[size];

        int sum = 0;
        float avg = 0;

        for( int i = 0; i < size; i++ ){
            System.out.printf("학생%d의 성적: ",i + 1);
            arr[i] = sc.nextInt();

            sum += arr[i];

        }
        avg = (float)sum / size;
        System.out.println("----------------");
        System.out.println("평균 성적 : " + avg);
        System.out.println("---평균 이상인 학생들---");

        int cnt = 0;
        for( int i = 0; i < size; i++ ){

            if (avg < arr[i]) {
                cnt++;
                System.out.printf("학생%d: %d\n",i+1,arr[i]);
            }

        }
        System.out.println("평균 이상인 학생 수 : " + cnt);

    }// main
}
