package ex7_homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ex1_Work {
    public static void main(String[] args) {
        
        //ArrayList를 두개 만들고 1 ~ 5사이의 난수를 5개 추가해둔다
        //두개 리스트의 값을 비교하여 교집합을 출력
        //------------------------------
        //[5, 3, 5, 1, 5] -> 첫번째 list
        //[2, 3, 1, 1, 3] -> 두번째 list
        //공통으로 가지고 있는 값(교집합) : [3, 1]

        Random rnd = new Random();

        //5개의 난수를 담는 list1,2
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();

        //교집합을 담기 위한 common
        List<Integer> common = new ArrayList<Integer>();

        for( int i = 0; i < 5; i++ ){
            list1.add(rnd.nextInt(5) + 1);
            list2.add(rnd.nextInt(5) + 1);
        }
        
        System.out.println(list1);
        System.out.println(list2);

        //개선된 루프
        for( int num : list1  ){  //list1에 있는 숫자를 하나씩 num에게 넘긴다

            if ( list2.contains(num) && !common.contains(num) ) { //list2에 num이 있고 common에 num이 없는거를 찾는 식
                common.add(num);
            }

        }

        System.out.println("교집합 : " + common);

    }//main
}
