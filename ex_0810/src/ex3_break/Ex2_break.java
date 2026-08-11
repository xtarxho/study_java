package ex3_break;

public class Ex2_break {
    public static void main(String[] args) {
        
        for( int i = 1; i <= 3; i++ ){

            switch( i ){
                case 1:
                    System.out.println( "case 1 ");
                    //switch문의 break는
                    //반복문이 아닌 현재 switch문을 빠져나가는
                    //용도로 사용된다.
                    break;

                case 2:
                    System.out.println("case 2");
                    break;





            }//switch


            System.out.println( "i의 값 : " + i);


        }//for





    }//main
}
