package ex1_multiArr;

public class Ex1_multiArr {
    public static void main(String[] args) {
        
        int[][] test = new int[2][3];
        test[0][0] = 100;
        test[0][1] = 200;
        test[0][2] = 300;

        test[1][0] = 400;
        test[1][1] = 500;
        test[1][2] = 600;
        
        for( int i = 0; i < test.length; i++ ){

            for( int j = 0; j < test[i].length; j++ ){

                System.out.print( test[i][j] + " " );

            }

            System.out.println();

        }

    }//main
}
