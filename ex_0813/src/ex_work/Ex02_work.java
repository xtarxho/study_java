package ex_work;

public class Ex02_work {
    public static void main(String[] args) {
        
        //배열 arr에서 3의 배수를 제외하고 출력
        //----------------------------
        //05 11
        //20 22
        //07 08
        int[][] arr = { { 9, 5, 11 }, 
                        { 12, 20, 21, 22 }, 
                        { 7, 8, 9 } };

        for( int i = 0; i < arr.length; i++ ){

            for( int j = 0; j < arr[i].length; j++ ){
                
                if( arr[i][j] % 3 != 0 ){
                    System.out.printf("%02d ",arr[i][j]); 
                    
                    
                }


            }
            System.out.println();
        }







        
    }//main

}
