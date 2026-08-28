package ex2_work;

import java.util.Scanner;

public class WorkMain {
    public static void main(String[] args) {
        
        //정수 : 7
        //7은 소수입니다

        //정수 : 9
        //9는 소수가 아닙니다

        Scanner sc = new Scanner(System.in);

        System.out.print("정수 : ");
        int num = sc.nextInt();

        WorkSub ws = new WorkSub();
        if(ws.isPrime(num) == true){
            System.out.println(num + " 소수");
        }else{
            System.out.println(num + " 안소수");
        }

        

    }//main
}
