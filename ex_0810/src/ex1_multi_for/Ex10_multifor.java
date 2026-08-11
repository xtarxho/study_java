package ex1_multi_for;

public class Ex10_multifor {
    public static void main(String[] args) {
        
        /* 
        1 3 5 7 9
        3 5 7 9 1
        5 7 9 1 3
        7 9 1 3 5
        9 1 3 5 7  
        */

        for( int i = 1; i <= 10; i+=2 ){

            
            for( int j = 0; j <= 9; j+=2 ){
                
                int num = i+j;
                
                //if(num > 10) 
                //num -= 10;  -> 이 방법도 있음
                System.out.print( num % 10 + " " );

            }
            System.out.println();
        }
        

        System.out.println("------------------------");

        for( int i = 0; i < 5; i++ ){


            for( int j = 0; j < 5; j++ ){
                int num = 1 + ( i * 2 ) + ( j * 2 );
                if( num > 10 )
                    num -= 10;

                System.out.print( num + " ");

            }
            System.out.println();

        }


        







    }
}

