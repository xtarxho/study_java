package ex7_work;

import java.util.Random;

public class UpdowmSub {
    
    //내가 푼 방법
    // public String Updowm(int su, int num, int cnt){
        
    //     while (su != num) {
    //         Scanner sc = new Scanner(System.in);
    //         System.out.print( "정수 :" );
    //         su = sc.nextInt();
    //         cnt++;

    //         if (su < num) {
    //             System.out.println("up");
    //         }else if (su > num) {
    //             System.out.println("down");
    //         }else{
    //         }
            
    //     }
    //     return (cnt + "회만에 정답");

    // }

    private int random = new Random().nextInt(50)+1;
    private int count = 0;
    private boolean result = true;

    //정답 판별 메서드
    public boolean check( int n ){

        count++;

        if (n < random) 
            System.out.println("UP");    
        else if(n > random)
            System.out.println("DOWN");
        else{
            System.out.println(count + "회 만에 정답");
            result = false;
        }

        return result;

    }//check









}
