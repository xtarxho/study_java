package ex7_work;

import java.util.Random;
import java.util.Scanner;

public class UpdownMain {
    public static void main(String[] args) {
        
        //클래스는 나눌 수 있으면 나눠본다
        //1 ~ 50 사이의 난수를 발생시키고
        //키보드에서 입력받은 정수를 난수와 비교하기
        //-------------------------------
        //정수 : 30
        //DOWN
        //정수 : 15
        //UP
        //정수 : 25
        //3회 만에 정답

        
        
        int num = new Random().nextInt(50) +1;
        int su = 0;
        int cnt = 0;
        

        UpdowmSub us = new UpdowmSub();
        String res = us.Updowm(su, num, cnt);
        System.out.println(res);



    }//main
}
