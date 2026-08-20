package ex01_work;

import java.util.Scanner;

public class NumberMain {
    public static void main(String[] args) {
        
        //값 : 100
        //100은(는) 숫자입니까? true

        //값 : abc123
        //abc123은(는) 숫자입니까? false

        Scanner sc = new Scanner(System.in);
        System.out.print( "값 : " );
        String str = sc.next();

        NumberSub ns = new NumberSub();
        boolean res = ns.isNumber(str); 

        System.out.println(str + "은 숫자입니까? : " + res);

    }//main
}
