package ex1_multi_for;

public class Ex5_multifor {
    public static void main(String[] args) {

        // 01 02 03 04 05
        // 02 04 06 08 10
        // 03 06 09 12 15
        // 04 08 12 16 20
        // 05 10 15 20 25

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 5; j++) {

                System.out.printf("%02d ", i * j);

            } // inner

            System.out.println();

        } // outer

    }// main
}
