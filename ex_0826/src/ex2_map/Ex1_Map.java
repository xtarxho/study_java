package ex2_map;

import java.util.HashMap;
import java.util.Map;

public class Ex1_Map {
    public static void main(String[] args) {

        // Map은 제네릭 타입이 두개이다
        Map<Integer, Character> map = new HashMap<>(); // 앞은 key타입 뒤에는 value타입
        map.put(10, 'A'); // put으로 저장한다
        map.put(11, 'C'); // put으로 저장한다
        map.put(12, 'A'); // put으로 저장한다

        // key값이 중복이 되면 기존 key값의 value를 갱신한다
        map.put(10, 'D'); // put으로 저장한다

        System.out.println("size : " + map.size()); // size()를 통해 put의 갯수 확인

        char res = map.get(11); // key값을 알고 있으면 value를 바로 꺼내올 수 있다
        System.out.println(res);

        System.out.println("map : " + map);

        if (map.containsKey(10)) { // 10이라는 key값을 포함하고 있냐는 뜻 -> containsKey
            System.out.println("map에 key가 10인 데이터가 있음");

        }

        if (map.containsValue('A')) { // ->value
            System.out.println("map에 A가 저장되어 있음");
        }

    }// main
}
