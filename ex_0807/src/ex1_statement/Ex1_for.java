package ex1_statement;

public class Ex1_for {
    public static void main(String[] args) {
        
        //for문 : 특정 코드를 원하는 만큼 반복하고자 할 때, 전역변수가 있으면 초기식을 안할수도 있음
        //for( 초기식; 조건식; 증감식 ){
        //  조건식이 참일 때 수행되는 영역
        //}


        //int i = 0; //전역변수 

        for(int i = 0; i <= 2; i++ ){ //조건식이 거짓이 될 때까지 계속 돈다
            //i는 for문에서만 사용 가능한 지역변수
            System.out.println(i);

        }//for

        System.out.println("------------------");
        
        //10~1까지 반복하는 for문 

        for( int a = 10; a >= 1; a--){
            System.out.println(a); 
        }//for
        

        

        System.out.println("--------------------");
        //1~100까지 반복하는 for문을 만들되
        //5의 배수만 출력

        //쌤이 알려주신 방법
        for( int i = 1; i <=100; i++ ){

            if( i % 5 == 0 ){ //이게 가장 많이 사용되는 방법
                System.out.println(i);
            }
        }//for


        //내가 푼 방법
        for( int i = 1; i <= 100 && i % 5 == 0; i++  ){
            System.out.println(i);
        }//for


        //쌤이 알려주신 다른 방법
        for(  int i = 5; i <= 100; i += 5 ){
            System.out.println(i);
        }









    }//main
}
