package ex6_work;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex3_Work {
    public static void main(String[] args) {
    
        //정수입력( 입력종료 : 0 )
        //>> 45
        //>> 17
        //>> 20
        //>> 21
        //>> 0 ->입력받으면 그 전까지 입력받은 정수들 list에 저장
        //--------------------------
        //숫자 범위를 입력하세요
        //시작수 : 15
        //끝 수 : 20
        //17 -> 15이상 20이하 데이터 출력
        //20 -> 15이상 20이하 데이터 출력

        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<Integer>();


        System.out.println("정수입력(입력종료 : 0)");
        while (true) {
            System.out.print(">> ");
            int num = sc.nextInt();
            
            if (num == 0) {
                break;
            }

            list.add(num);
        }
        System.out.println("------------------------");

        System.out.print("시작 수 : ");
        int start = sc.nextInt();

        System.out.print("끝 수 : ");
        int end = sc.nextInt();
        
        for( int i = 0; i < list.size(); i++ ){

            if (start <= list.get(i) && end >= list.get(i)) {
                System.out.println(list.get(i));
            }

        }
        
    }//main

}
