package ex9_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListMain {
    public static void main(String[] args) {
        
        //학생수 : 2
        //-------------
        //이름 : 홍길동
        //국어 : 99
        //영어 : 87
        //------------------
        //이름 : 임길동
        //국어 : 75
        //영어 : 80
        //---------------
        //홍길동    국어:99    영어:87    총점:186    평균:93.0
        //임길동    국어:75    영어:80    총점:155    평균:77.5
        //반 전체 평균 : 85.25

        Scanner sc = new Scanner(System.in);
        List<ListSub> list = new ArrayList<ListSub>();
        ListSub stu;

        System.out.print("학생수 : ");
        int cnt = sc.nextInt();

        for( int i = 0; i < cnt; i++ ){
            
            System.out.print("이름 : ");
            String name = sc.next();

            System.out.print( "국어 : " );
            int kor = sc.nextInt();

            System.out.print("영어 : ");
            int eng = sc.nextInt();

            stu = new ListSub(name, kor, eng);
            list.add(stu);

            System.out.println("--------------------");
        }//for

        //반 전체평균
        float all_avg = 0;

        //학생정보 출력
        for( int i = 0; i < list.size(); i++ ){

            list.get(i).ListSubinfo();

            all_avg += list.get(i).getAvg();

        }//for

        System.out.printf("반 전체 평균 : %.2f",all_avg / list.size());


    }//main
}
