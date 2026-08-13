package ex_work;

public class Ex04_work {
    public static void main(String[] args) {
        
        //2차원 배열을 이용해 다음의 결과 출력하기
        //-----------------------
        //0 1 2 3
        //1 2 3 4
        //2 3 4 5
        //3 4 5 6

        int[][] arr = new int[4][4];

        for( int i = 0; i < arr.length; i++ ){

            for( int j = 0; j < arr[i].length; j++ ){
                
                arr[i][j] = i + j;
                System.out.print( arr[i][j] + " " );
                
                }//inner

                System.out.println();
            }//outer
        







    }//main
}
