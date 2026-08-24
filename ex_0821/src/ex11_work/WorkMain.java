package ex11_work;

import java.util.Scanner;

public class WorkMain {
    public static void main(String[] args) {
        
        //0821 숙제

        //준비해놓은 단어를 랜덤으로 섞어서 출력

        //문제 : PEALP
        //입력 >> ealpp
        //ealpp 오답
        //입력 >> apple
        //apple 정답!
        WorkSub ws = new WorkSub();
        String answer = ws.getAnswer();

        //정답을 섞어서 문제로 맏르기
        String question = ws.scrambleWord(answer);

        Scanner sc = new Scanner(System.in);

        System.out.println("문제 : " + question);

        while (true) {
            
            System.out.print( ">> " );
            String str = sc.next();

            if (str.equalsIgnoreCase(answer)) {
                System.out.println(str + "은 정답입니다");
                break;
            }else{
                System.out.println(str + "은 오답입니다");
            }

        }

    }//main
}
