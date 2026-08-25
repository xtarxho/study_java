package ex4_work;

import java.util.Scanner;

public class ThreadMain {
    public static void main(String[] args) {
        
        //키보드에서 정수를 입력받고
        //입력받은 숫자가 1초에 1씩 감소되면서 출력되다가
        //0이 되었을때 프로그램을 종료
        //(Sub클래스는 반드시 Thread를 상속받도록 한다)
        //-----------------------
        //입력 : 3
        //3
        //2
        //1
        //0

        
        Scanner sc = new Scanner(System.in);
        System.out.print("입력 : ");
        int n = sc.nextInt(); // 이 값을 서브로 보내야된다 
        
        ThreadSub ts = new ThreadSub(n); 
        ts.start();



    }//main
}
