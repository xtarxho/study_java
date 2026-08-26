package ex6_work;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ex1_Work {
    public static void main(String[] args) {

        // ArrayList arr에 1 ~ 30 사이의 난수를 5개 추가
        // arr에 담겨진 값 중 가장 큰 값 출력
        // --------------------
        // [16,13,29,24,14]
        // 가장 큰 값 : 29

        List<Integer> arr = new ArrayList<Integer>();
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) {
            int res = rnd.nextInt(30) + 1;
            arr.add(res);

            
        }//for
        
        System.out.println(arr);

        int max = arr.get(0);

        for( int i = 1; i < arr.size(); i++ ){

            if ( arr.get(i) > max ) {
                max = arr.get(i);
            }

        }
        System.out.println("가장 큰 값 : " + max);

    }// main
}
