public class Ex04_work {
    public static void main(String[] args) {

        // 1 ~ 100까지 반복하는 for문을 만들되
        // 5의 배수만 출력

        for (int i = 1; i <= 100; i++) {
            if (i % 5 == 0) {
                System.out.print(i + " ");
            }

        }

    }// main
}
