package ex3_break;

public class Ex1_break {
    public static void main(String[] args) {
        
        //break문 : 반복문 내에서 강제적으로
        //가장 가까운 반복문을 빠져나갈 때 사용하는 키워드

        for( int i = 1; i <= 3; i++ ){


            for( int j = 1; j <= 10; j++ ){

                if( j % 2 == 0 ){
                    break;

                }
                System.out.print(j + " ");

            }//inner

            System.out.println();

        }//outer







    }//main
}
