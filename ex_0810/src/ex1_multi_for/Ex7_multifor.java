package ex1_multi_for;

public class Ex7_multifor {
    public static void main(String[] args) {

        // 2 x 1 = 2 3 x 1 = 3 4 x 1 = 4 ... 9 x 1 = 9
        // ....
        // 2 x 9 = 18 3 x 9 = 27 .........9 x 9 = 81

        for (int i = 1; i <= 9; i++) {

            for (int j = 2; j <= 9; j++) {
                // if (i <= j) {
                System.out.print(j + " x " + i + " = " + j * i + " ");
                // }

            } // inner

            System.out.println();

        } // outer

    }// main
}
