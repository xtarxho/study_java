package ex1_set;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Ex2_Set {
    public static void main(String[] args) {
        
        TreeSet<Integer> ts = new TreeSet<>();
        Random rnd = new Random();

        while (ts.size() < 6) {
            int r = rnd.nextInt(45)+1;
            ts.add(r);        
        }
        System.out.println(ts);
        System.out.println(ts.subSet(10,30)); 

        System.out.println("------------------");

        //한글도 오름차순 정렬을 한다
        Set<String> set = new TreeSet<String>();
        set.add("홍길동");
        set.add("강길순");
        set.add("고봉민");
        System.out.println(set);

    }//main
}
