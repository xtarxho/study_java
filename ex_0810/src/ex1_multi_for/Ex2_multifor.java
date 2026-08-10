package ex1_multi_for;

public class Ex2_multifor {
    public static void main(String[] args) {
        
        //01 02 03 04
        //05 06 07 08
        //09 10 11 12

        //복습하기
        int cnt = 0;


        for( int i = 1; i <= 3; i++ ){
            
            

            for( int j = 1; j <= 4; j++ ){

                System.out.printf("%02d ", ++cnt); // -> 꼭 FOR안에 있는 변수 사용 안해도된다. 

            }//inner

            System.out.println();

        }//outer














    }
}
