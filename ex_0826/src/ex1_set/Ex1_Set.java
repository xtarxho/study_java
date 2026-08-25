package ex1_set;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ex1_Set {
    public static void main(String[] args) {

        // collection:
        // 많은 수의 데이터를 그 사용 목적에 적합한 구조로 묶어서
        // 하나로 그룹화 한 객체
        // -------------
        // Set, Map, List는 collection에 속해있는 대표적인 인터페이스

        // Set은 java.util패키지의 인터페이스다
        // 특정 코드에서 중복된 값의 허용이 있어서는 안될 때 사용하는 객체
        // 복잡한 코드없이 중복요소를 빠르게 제거할 수 있다

        // Set인터페이스를 구현하고 있는 대표적인 자식클래스
        // HashSet : 정렬이 안됨
        // TreeSet : 오름차순 정렬을 해버린다
        //주소값이 연결되어 있지 않다 -> 배열은 주소값이 연결되어 있다
        Set<Integer> set = new HashSet<Integer>(); //제네릭 타입
        set.add(150);
        set.add(70);
        set.add(10);
        set.add(200);

        //중복된 값은 추가하지 않는다
        set.add(10);

        //set의 모든 내용을 비운다
        set.clear(); //->값을 지워준다

        System.out.println("set의 크기 : " + set.size());
        System.out.println(set);

        System.out.println("---------------------");

        Set<Integer> set2 = new HashSet<Integer>();
        Random rnd = new Random();

        while (true) {
            
            int r = rnd.nextInt(45) + 1;
            set2.add(r);

            if (set2.size() == 6) {
                break;
            }


        }//while

        System.out.println(set2);

        //set2를 배열로 변환하기
        //0개짜리 방을 만든다는 뜻이 아니라 set2가 가지고 있는 정보를 정수 타입의 배열을 만들어준다
        Integer[] arr = set2.toArray(new Integer[0]); 

        for( int i = 0; i < arr.length; i++ ){
            System.out.print(arr[i] + " ");
        }

        System.out.println("\n------------------");

        int[] arr2 = new int[set2.size()];
        int i = 0;
        for( Integer num : set2 ){ //개선된 루프
            arr2[i++] = num;
            
        }

        for( int j = 0; j < arr2.length; j++ ){
            System.out.print( arr2[j] + " " );

        }

    }// main
}
