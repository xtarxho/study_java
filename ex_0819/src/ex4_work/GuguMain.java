package ex4_work;

import java.util.Scanner;

public class GuguMain {
    
    public static void main(String[] args) {
        
        //단 : 5
        // 5단
        // 5 x 1 =5
        //...
        // 5 x 9 = 45
      
        Scanner sc = new Scanner(System.in);
        System.out.print("단 : ");
        int dan = sc.nextInt();
        
        Gugudan ggd = new Gugudan();
        ggd.printGugu(dan);

      







    }//main
}
