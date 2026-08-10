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

            for (int j = 1; j <= 10; j++) {
                
                if( i <= j && j <= 10 ){
                    System.out.print(j);
                    
                }else if( j >= 10 ){
                    
                }


            } // inner
            System.out.println();
       
        } // outer

    }

}// main
