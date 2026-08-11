package ex1_multi_for;

public class Ex8_multifor {
    public static void main(String[] args) {

        /*
         * 1 2 3 4 5 6 7 8 9 10
         * 2 3 4 5 6 7 8 9 10 1
         * 3 4 5 6 7 8 9 10 1 2
         * 
         * .........
         * 10 1 2 3 4 5 6 7 8 9
         */

        for (int i = 1; i <= 10; i++) {

            for (int j = 0; j < 10; j++) {
                System.out.print((i + j - 1) % 10 + 1 + " ");

            }

            System.out.println();

        } // outer
        System.out.println("------------------------------");

        // 쌤이 알려주신 방법
        for (int i = 1; i <= 10; i++) {

            for (int j = 0; j < 10; j++) {
                int num = i + j;

                if (num > 10)
                    num -= 10; // 한줄만 실행하는거면 중괄호 안넣어도된다

                System.out.print(num + " ");
            }

            System.out.println();

        }

    }

}// main
