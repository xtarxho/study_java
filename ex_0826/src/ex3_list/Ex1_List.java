package ex3_list;

import java.util.ArrayList;
import java.util.List;

public class Ex1_List {
    public static void main(String[] args) {
        
        //list는 index 번호가 있다
        //list 객체는 스택에 만들어진다
        //중복이 되어도 계속 add한다
        List<Integer> list = new ArrayList<Integer>();
        list.add(100); //index 0
        list.add(100); //index 1
        list.add(50); //index 2
        list.add(60); //index 3

        // 값을 사이에 넣는 것이다 index가 밀려난다 뒤로 한칸씩
        list.add(1, 60); //내가 추가하고 싶은 방 번호를 지정해서 값을 넣을 수 있음
        list.set(0, 90); //set을 사용하면 특정 index값을 변경할 수 있다
        list.remove(2); // 값을 제거해주고 인덱스가 한칸씩 땡겨진다

        if (list.contains(50)) { //list안에 50이라는 값을 가지고 있냐는 뜻이다
            System.out.println("list에 50이 있다");
        }


        System.out.println("size : " + list.size()); //list의 사이즈 -> 3이 나옴
        System.out.println(list);
        System.out.println("2번 index값 : " + list.get(2)); //list 2번 index값을 가져온다

        list.clear(); //list를 전부 없애준다
        System.out.println("size : " + list.size());

    }//main
}
