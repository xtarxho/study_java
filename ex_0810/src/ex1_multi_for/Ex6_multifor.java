package ex1_multi_for;

public class Ex6_multifor {
    public static void main(String[] args) {

        // *
        // * *
        // * * *
        // * * * *
        // * * * * *

        for (int i = 1; i <= 5; i++) {

            for (int j = 0; j < i; j++) {

                System.out.print("* ");

            }//inner
            System.out.println();
        }//outer

        System.out.println();

    }//main

}
