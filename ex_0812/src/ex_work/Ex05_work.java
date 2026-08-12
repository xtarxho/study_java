package ex_work;

import java.util.Scanner;

public class Ex05_work {
    public static void main(String[] args) {
        
        //학생 수를 입력하세요 : 3                                 
        //학생 1의 성적: 90         
        //학생 2의 성적: 73
        //학생 3의 성적: 84
        //-----------------
        //평균 성적: 82.333336
        //---평균 이상인 학생들---
        //=> 학생1: 90
        //=> 학생3: 84
        //평균 이상인 학생 수: 2

        Scanner sc = new Scanner(System.in);
        System.out.print( "학생 수 : ");
        int st = sc.nextInt();

        //성적을 입력받아 저장하기 위한 배열
        int[] num = new int[st];
        
        //총점을 저장할 변수
        int sum = 0;

        for( int i = 0; i < st; i++ ){
            System.out.printf("학생%d의 성적 : ", i + 1);
            num[i] = sc.nextInt();
            sum += num[i];
                
        }
        System.out.println("--------------------------------");
        float avg = (float)sum / st;
        System.out.printf( "평균성적 : %.2f\n", avg );

        System.out.println("---평균 이상인 학생들---");
        
        int avgcnt = 0; //평균 이상인 학생수를 담을 변수
        
        for( int i = 0; i < st; i++ ){
            if( num[i] > avg ){
                avgcnt++;
                System.out.printf( "학생%d: %d\n",i+1,num[i]  );
            }

        }

        System.out.println("평균 이상의 학생 수 : " + avgcnt);







    }//main
}
