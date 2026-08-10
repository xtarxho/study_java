package ex1_multi_for;

public class Ex9_multifor {
    public static void main(String[] args) {
        
/*

            *
          * * *
        * * * * *
      * * * * * * *
    * * * * * * * * *

*/


        for(int i = 1; i <= 5; i++){

            for( int j = 1; j <= 9; j++ ){
                if( i % 2 == 1 ){
                    System.out.print("*");
                }


            }
            System.out.println();
        }







    }//main
}
