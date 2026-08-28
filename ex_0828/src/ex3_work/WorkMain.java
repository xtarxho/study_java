package ex3_work;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class WorkMain {
    public static void main(String[] args) {
        
        //1~45사이의 중복되지 않는 난수 6개를 출력하는 
        //로또번호 생성기 만들기

        //Set : 중복값을 허용하지 않는 자바의 interface
        //TreeSet : 중복값 허용 X, 오름차순 정렬

        Random rnd = new Random();

        Set<Integer> set = new TreeSet<Integer>();

        while( set.size() < 6 ){

            set.add(rnd.nextInt(45)+1);

        }//while

        System.out.println(set);

        //set에 담긴 내용을 정수배열에 복사해서 넣어보기
        Integer[] arr = set.toArray(new Integer[0]);

        for( int num : arr ){
            System.out.print(num + " ");

        }

    }//main
}
