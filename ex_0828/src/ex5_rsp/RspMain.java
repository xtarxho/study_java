package ex5_rsp;

import java.util.Random;
import java.util.Scanner;

public class RspMain {
    public static void main(String[] args) {

        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        User user = new User();

        int win = 0;
        int lose = 0;
        int draw = 0;

        System.out.print("id : ");
        String id = sc.next();

        //입력받은 id를 user객체에 저장
        user.setId(id);

        //파일 읽기
        ScoreLoad sl = new ScoreLoad();
        user = sl.load(user);

        win = user.getWin();
        lose = user.getLose();
        draw = user.getDraw();

        System.out.printf("%d승, %d패, %d무\n",win, lose, draw);

        while(true){
            //0:가위, 1:바위, 2:보
            int random = rnd.nextInt(3);

            System.out.print("가위(0) | 바위(1) | 보(2) : ");
            int iam = sc.nextInt();

            //경우의 수
            if (iam - random == -2 || iam - random == 1) {
                System.out.println("이겼습니다");
                user.setWin(++win);
            }else if (iam - random == 0) {
                System.out.println("비겼습니다");
                user.setDraw(++draw);
            }else{
                System.out.println("졌습니다");
                user.setLose(++lose);
            }

            System.out.printf("%d승 %d패 %d무\n",
                            user.getWin(), user.getLose(),user.getDraw());

            System.out.print("리겜? y | n : ");
            if (sc.next().equals("n")) {
                break;
            }

        }//while

        System.out.println("게임종료");

        //사용자의 기록을 저장
        ScoreWrite sw = new ScoreWrite();
        sw.save(user);
    }//main
}
