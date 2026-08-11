package ex2_while;

import java.util.Random;
import java.util.Scanner;

public class Ex3_while {
    public static void main(String[] args) {

        // 1 ~ 100사이의 난수를 만들고
        // 키보드에서 입력받은 값이 정답일 때 게임을 종료
        // --------------------------------
        // 숫자 : 25
        // UP
        // 숫자 : 70
        // DOWN
        // 숫자 : 50
        // 정답입니다

        Scanner sc = new Scanner(System.in);
        int num = 0; // 사용자가 입력할 값
        int game = new Random().nextInt(100) + 1;
        //System.out.println(game); //정답 보여줌
        while (num != game) /* num값이 game값이랑 다르면 계속 반복 */ {
            System.out.print("숫자 : ");
            num = sc.nextInt();

            if (num < game) {
                System.out.println("UP");
            } else if (num > game) {
                System.out.println("DOWN");
            } else {
                System.out.println("정답입니다");
            }

        } // while


        //풀어보기
        // 2. 숫자 마블 게임
        // 시스템이 1~30 중에 하나의 숫자를 고릅니다.
        // 사용자가 한 번에 1, 2, 3 중 원하는 만큼 숫자를 더하면서 입력합니다.
        // 입력한 누적합이 시스템이 고른 숫자와 같아지는 사람이 이깁니다.
        // (예: 목표는 17. 입력: 3 -> 6 -> 8 ... 17, 정답!)
        //





        // 3. 홀짝 맞추기 게임
        // 10번의 라운드 동안 컴퓨터가 1~20 사이의 난수를 만듭니다.
        // 사용자는 '홀' 혹은 '짝'을 입력합니다.
        // 컴퓨터의 수가 홀수/짝수와 맞으면 점수 1점!
        // 최종 점수는 몇 점일까요?








    }// main

}
