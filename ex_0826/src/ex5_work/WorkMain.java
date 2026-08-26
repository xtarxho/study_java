package ex5_work;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkMain {
    public static void main(String[] args) {
        // ------------중요함-------------
        // id : aaa
        // pw : 1111
        // aaa/1111
        // ------------------
        // id : bbb
        // pw : 2222
        // aaa/1111
        // bbb/2222
        // id : bbb
        // pw : 2222
        // 아이디가 중복됩니다
        // -----------------
        // id :

        Scanner sc = new Scanner(System.in);
        List<User> list = new ArrayList<User>(); // 제네릭 타입에는 class도 들어갈 수 있다

        out: while (true) {

            System.out.print("id : ");
            String id = sc.next();

            // 중복체크
            for (int i = 0; i < list.size(); i++) {

                // list안에 0번째 index를 가져오고 0번째 index안에 있는 id를 가져와서 내가 입력한 id랑 같은지 비교해준다
                if (list.get(i).getId().equals(id)) {
                    System.out.println("아이디가 중복됩니다");
                    continue out;
                }

            }

            System.out.print("pw : ");
            int pw = sc.nextInt();

            // new -> 명시적. 새로 집을 다시 만든다
            User us = new User(); // us가 있으면 비번이 뭔지 아이디가 뭔지 알 수 있다
            us.setId(id);
            us.setPw(pw);

            list.add(us); // list에 us객체를 넣어준다

            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getId() + "/" + list.get(i).getPw());
            }
            System.out.println("-------------------------------");
        }

    }// main
}
