package ex1_multi_for;

public class Ex9_multifor {
    public static void main(String[] args) {

        /*
         *
         * 
         * * *
         * * * * *
         * * * * * * *
         * * * * * * * * *
         * 
         */

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 9; j++) {
                if (j >= 6 - i && j <= 4 + i) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }

            }

            System.out.println();
        }
        System.out.println("-----------------------------");
       
       //쌤이 알려주신 방법
        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5 + i; j++) {
                
                if ( i + j > 3 ) {
                    System.out.print( "* ");
                }else{
                    System.out.print("  ");
                }
                

            }

            System.out.println();

        }

    }// main
}
