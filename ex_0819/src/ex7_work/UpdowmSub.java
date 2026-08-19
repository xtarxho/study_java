package ex7_work;

import java.util.Scanner;

public class UpdowmSub {
    
    public String Updowm(int su, int num, int cnt){
        
        while (su != num) {
            Scanner sc = new Scanner(System.in);
            System.out.print( "정수 :" );
            su = sc.nextInt();
            cnt++;

            if (su < num) {
                System.out.println("up");
            }else if (su > num) {
                System.out.println("down");
            }else{
            }
            
        }
        return (cnt + "회만에 정답");

    }

}
