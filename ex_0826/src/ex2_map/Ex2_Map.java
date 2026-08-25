package ex2_map;

import java.util.HashMap;
import java.util.Map;

public class Ex2_Map {
    public static void main(String[] args) {
        
        Map<Character, String> map = new HashMap<>();
        map.put('a',"안녕하세요");
        map.put('b', "반가워요");
        map.put('c', "홍길동");
        
        map.put('b', "반가워");
        System.out.println(map);

        String res = map.get('c'); //c의 value값을 알아낼 수 있다
        System.out.println(res);

        map.clear();
        System.out.println("size : " + map.size());
        
    }//main
}
