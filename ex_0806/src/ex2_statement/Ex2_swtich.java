package ex2_statement;

public class Ex2_swtich {
    public static void main(String[] args) {
        
        //swtich는 비교값 타입과 조건값 타입이 같아야함

        String str = "A";
        String res = "";

        switch( str ){ //비교값

            case "A": //조건값
                res = "90~100";
                break;   // break를 만날때까지 내려감

            case "B":
                res = "80~89";
                break;

            case "C":
                res = "70~79";
                break;

            case "D":
                res = "60~69";
                break;

            case "F":
                res = "59이하";
                break;

            default:
                res = "올바른 성적을 입력하세요";
                break;
        }//switch

        
        System.out.println(res);


    }//main
}
