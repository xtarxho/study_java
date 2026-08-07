package ex_work;

import java.util.Scanner;

public class Ex2_work {
    public static void main(String[] args) {
        
        //주말 숙제

        //키보드에서 입력받은 2~9사이의 값에 해당하는
        //구구단을 출력
        //-------------------
        //단 : 10
        //2~9사이에 값을 입력하세요

        //단 : 5
        //5*1=5
        //5*2=10
        //....
        //5*9=45

        Scanner sc = new Scanner(System.in);

        System.out.print( "단 : ");
        int dan = sc.nextInt();
        
        
        //모르겠음
       for( int i = 1; i <= 9; i++ ){
            System.out.println(dan + "*" + i + "=" + dan * i);
       }


            
        
        




    }//main
}
