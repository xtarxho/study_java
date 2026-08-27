package ex8_homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkMain {
    public static void main(String[] args) {
        //3초 간격으로 단어를 추가하는 타자연습게임 만들기
        /*
         * [strawberry]
         * >> strawberry
         * [orange, grape, apple]
         * >> orange
         * [grape, apple]
         * >> grape
         * [apple, apple]
         * >> apple
         * [apple]
         * >> apple
         * [strawberry]
         * >> strawberry
         * 클리어!!!
         */
        String[] data = { "apple", "banana", "grape", "strawberry", "pineapple"};
        List<String> arr = new ArrayList<String>();

        WorkSub ws = new WorkSub(arr, data); //Sub로 main에 있는 arr과 data가 넘어간다
        ws.setDaemon(true); 
        ws.start(); // start는 run이라는 메서드가 백그라운드(Sub)에서 실행됬다는 뜻

        //1번째 방법
        // try{
        //     Thread.sleep(100);
        // }catch(Exception e){

        // }

        //2번째 방법
        Scanner sc = new Scanner(System.in);

        while (true) {
            
            if ( arr.size() == 0 ) {
                System.out.println("클리어!!!");
                break;
            }

            System.out.println(arr);
            System.out.print(">> ");
            String in = sc.next();

            //사용자가 입력한 단어와 arr에 담긴 값을 비교하기
            for( int i = 0; i < arr.size(); i++ ){

                if ( in.equals(arr.get(i)) ) {
                    arr.remove(i);
                    break;
                }//if

            }//for

        }//while


    }//main
}
