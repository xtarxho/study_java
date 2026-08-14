import java.util.Random;

public class Ex08_work {
    public static void main(String[] args) {

        // 1 ~ 45사이의 중복되지 않는 난수 6개를 출력하는
        // 로또번호 생성기 만들기
        // -------------------
        // 17 2 45 27 9 11
        int[] lotto = new int[6];

        
        outer: for (int i = 0; i < lotto.length;) {

            lotto[i] = new Random().nextInt(45 - 1 + 1) + 1;
            // 중복된 값 비교하기 위해 for문을 하나 더 쓴다
            for (int j = 0; j < i; j++) {
                if (lotto[i] == lotto[j]) {
                    continue outer;
                }

            } // inner
            System.out.print(lotto[i] + " ");
            i++;

        } // outer

    }// main
}
