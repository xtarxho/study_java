package ex_0806;

import java.util.Random;

public class Ex2_Work {
    public static void main(String[] args) {

        // 비교값으로 1 ~ 12월 사이의 값을 준비
        // 준비된 비교값에 해당하는 달이 몇일까지 있는지 출력
        // ---------------
        // 3월은 31일까지 있습니다

        //내가 그냥 랜덤을 사용해서 풀어봄
        int month = new Random().nextInt(12);

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println(month + "월은 31일까지");
                break;
            
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println(month + "월은 30일까지");
                break;

            default:
                System.out.println("2월은 28일까지");
                break;
        }

    }// main
}
