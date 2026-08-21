package ex07_work;

public class PrintGraph {

    public void print(int[] arr){

        for( int i = 0; i < arr.length; i++ ){

            System.out.print( i + "의 갯수 : " );
            
            for( int j = 0; j < arr[i]; j++ ){ //왜 arr[i]인지 모르겠음
                System.out.print( "#" );

            }//inner

            System.out.println(" " + arr[i]);

        }//outer

    }

}
