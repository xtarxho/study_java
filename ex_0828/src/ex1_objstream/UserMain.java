package ex1_objstream;

import java.util.Scanner;

public class UserMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 정보가 들어가있지 않지만 메모리 할당만 받았다
        User user = new User(); // user 객체를 만든다

        System.out.println("1.새로등록");
        System.out.println("2.불러오기");
        System.out.print(">> ");

        int sel = sc.nextInt();

        switch (sel) {
            case 1: // 유저등록
                System.out.print("이름 : ");
                // 메모리 할당 받아논 친구한테 세팅을 한다
                user.setName(sc.next());

                System.out.print("나이 : ");
                // 메모리 할당 받아논 친구한테 세팅을 한다
                user.setAge(sc.nextInt());

                System.out.print("전화 : ");
                // 메모리 할당 받아논 친구한테 세팅을 한다
                user.setTel(sc.next());

                UserWriter uw = new UserWriter();
                uw.writeInfo(user); // 메모리 할당이 되어있다
                break;
            case 2: // 불러오기
                System.out.print("전화 : ");
                String tel = sc.next();

                UserLoader ul = new UserLoader();
                user = ul.loadInfo(tel);

                if (user != null) {
                    System.out.println("이름 : " + user.getName());
                    System.out.println("나이 : " + user.getAge());
                    System.out.println("전화 : " + user.getTel());
                }
                break;

        }

    }// main
}
