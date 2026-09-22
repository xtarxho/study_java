package ex_0814;

import java.util.Random;
import java.util.Scanner;

public class Ex1_Baseball {
    public static void main(String[] args) {

        // 1 ~ 9 사이의 중복되지 않는 난수를 3개를 받아서 맞추기
        // 결과
        // 세자리 수를 입력하세요(예:123) - 123
        // 1Strike, 1Ball
        // --------------------------
        // 세자리 수를 입력하세요(예:123) - 567
        // OUT!!
        // --------------------------
        // 세자리 수를 입력하세요(예:123) - 214
        // 0Strike, 3Ball
        // --------------------------
        // 세자리 수를 입력하세요(예:123) - 142
        // 4회 정답 !! - 142

        Scanner sc = new Scanner(System.in);

        int[] com = new int[3];
        int[] user = new int[3];

        out : for( int i = 0; i < com.length; ){

            com[i] = new Random().nextInt(9) + 1;

            for( int j = 0; j < i; j++ ){

                if (com[i] == com[j]) {
                    continue out;
                }

            }
            i++;
        }
        System.out.println("정답 -> " + com[0] + com[1] + com[2]);


        int cnt = 0;

        while (true) {
            cnt++;
            System.out.print("세자리 수 입력 : ");
            int num = sc.nextInt();

            user[0] = num / 100;
            user[1] = num / 10 % 10;
            user[2] = num % 10;

            int strike = 0;
            int ball = 0;

            for( int i = 0; i < com.length; i++){

                for( int j = 0; j < com.length; j++ ){

                    if (i == j) {
                        if(com[i] == user[i])
                            strike++;
                    }else{
                        if(com[i] == user[j])
                            ball++;
                    }   

                }

            }

            if (strike == 3) {
                System.out.println("정답 : " + user[0] + user[1] + user[2]);
                System.out.println(cnt + "회만에 클리어");
                break;
            }else{
                if(strike > 0 || ball > 0){
                    System.out.printf("%d strike, %d ball\n",strike, ball);
                }else{
                    System.out.println("out");
                }
            }
            System.out.println("----------------------");
        }

    }// main
}