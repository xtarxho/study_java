package ex4_work;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex1_Work {
    public static void main(String[] args) {

        // 무한반복코드 만들기
        // id : aaa
        // aaa
        // id : bbb
        // aaa
        // bbb
        // id : ccc
        // aaa
        // bbb
        // ccc
        // id : aaa
        // aaa 회원은 이미 존재함
        // id :
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<String>();

        outer : while (true) {
            System.out.print("id : ");
            String id = sc.next();

            // 중복체크 1번
            // if (list.contains(id)) {
            //     System.out.println(id + " 회원은 이미 있어");
            //     continue;
            // }

            //중복체크 2번
            for( int i = 0; i < list.size(); i++ ){

                if ( id.equals(list.get(i)) ) {
                    System.out.println(id + "회원은 이미 있어");
                    continue outer;
                }

            }//for

            list.add(id); //add하기전에 중복체크를 해야한다

            // 전체회원
            for (int i = 0; i < list.size(); i++) {

                System.out.println(list.get(i) + " 회원");

            }

        }

    }// main
}
