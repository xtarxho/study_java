package ex2_statement;

public class Ex3_switch {
    public static void main(String[] args) {
        
        char name = '김'; 

        switch( name ){ //비교값

            case '김': //조건값
                System.out.println("1000만");
                //break;                     //System.out.println을 사용하면 break가 없을 때 내려가면서 모두 입력된다

            case '이':
                System.out.println("730만");
                //break;

            case '박':
                System.out.println("419만");
                //break;

            default:
                System.out.println("데이터가 없습니다");
                break;
        }//switch






    }//main
}
