package ex2_statement;

public class Ex1_switch {
    public static void main(String[] args) {

        //switch-case문  -> 제어문 (비교값을 쓸 수 있는 타입이 정수(byte, short, int 사용 가능), String(문자열), char(문자))
        //비교값과 조건값을 통해서 원하는 결과를 얻어내기 위한 제어문
        //if문보다 switch문이 더 빠름 -> 아래로 내려가면서 찾는게 아니라 바로 찾아버림
        //범위 지정을 못함 1점부터 100점까지 100개를 다 만들어야함 

        //switch( 비교값 ){
        //  case 조건값:
        //   비교값과 조건값이 일치할 때 실행되는 영역   ->   여기가 실행코드 
        //  break;
        //}

        int n = 4;
        switch( n ){  //비교값
            case 1: //조건값이라고 부름(중복되는 조건값 사용금지XXX)
                System.out.println("게임시작");
                break;

            case 2:
                System.out.println("설정");
                break; //break를 만나면 switch문을 나간다

            case 3:
                System.out.println("종료");
                break;

            default:
                //비교값과 일치하는 조건값이 하나도 없을 때
                //반드시 호출되는 영역
                System.out.println("올바른 값을 입력하세요");
                break;

        }//switch





    }// main
}
