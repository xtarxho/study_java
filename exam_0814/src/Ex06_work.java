import java.util.Scanner;

public class Ex06_work {
    public static void main(String[] args) {

        // 정수 : 7
        // 7은(는) 소수입니다

        // 정수 : 10
        // 10은(는) 소수가 아닙니다

        Scanner sc = new Scanner(System.in);
        System.out.print("정수 : ");
        int num = sc.nextInt();

        //내가 푼 방법 -> 틀림 
        // for (int i = 2; i < num; i++) {
        //     if (num % i == 0) {
        //         System.out.print(num + "은(는) 소수가 아닙니다");
        //     } else {
        //         System.out.print(num + "은(는) 소수입니다");
        //     }
        //     break;
        // }
        


        //쌤이 알려주신 방법 -> 정답
        int i = 2;

        for( ; i <= num; i++ ){
            if( num % i == 0 ){
                break;
            }
        }//for

        if( i == num){

            System.out.print( num + "은 소수" );

        }else{
            System.out.print( num + "은 소수 아님" );
        }








    }// main
}
