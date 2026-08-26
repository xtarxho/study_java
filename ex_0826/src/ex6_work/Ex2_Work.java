package ex6_work;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex2_Work {
    public static void main(String[] args) {

        // 5개의 문장을 입력받아 ArrayList에 저장하고
        // 가장 긴 문자열을 찾아 출력
        // ------------------------
        // 입력 : aaa
        // 입력 : bbbb
        // 입력 : cccc
        // 입력 : vvvv
        // 입력 : bbbbb
        // 가장 긴 문자열 : bbbbb

        List<String> list = new ArrayList<String>();

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.print("입력 : ");

            //list.add(sc.next()); -> 밑에 있는거 단축시킨거 쌤이 알려주신 거
            String str = sc.next();
            list.add(str);

        }

        String lo = "";
        for (int i = 0; i < list.size(); i++) {
            //String str = list.get(i); 쌤이 알려주신거
            //if문에 str.length() > lo.length() 작성
            //lo = str; 작성
            if (list.get(i).length() > lo.length()) {
                lo = list.get(i);
            }
        }
        System.out.println("가장 긴 문자열 : " + lo);
    }// main
}
