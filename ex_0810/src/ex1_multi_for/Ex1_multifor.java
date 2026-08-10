package ex1_multi_for;

public class Ex1_multifor {
    public static void main(String[] args) {

        // 1 2 3 4 5
        // 1 2 3 4 5
        for (int i = 1; i <= 3; i++) { // y축 (행)

            for (int j = 1; j <= 5; j++) { // x축 (열)

                System.out.printf("%d ", j);

            } // inner (안쪽 for문)

            System.out.println();

        } // outer (바깥쪽 for문)

        System.out.println("--------------------");

        // 5 4 3 2 1
        // 5 4 3 2 1
        // 5 4 3 2 1

        for (int i = 3; i >= 1; i--) {
            for (int j = 5; j >= 1; j--) {
                System.out.print(j + " ");
            } // inner

            System.out.println();

        } // outer

        // 쌤이 알려주신 방법
        for (int i = 1; i <= 3; i++) {

            for (int j = 5; j >= 1; j--) {

                System.out.print(j + " ");

            }

            System.out.println();

        }

    }
}
