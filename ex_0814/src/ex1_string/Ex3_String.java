package ex1_string;

public class Ex3_String {
    public static void main(String[] args) {
        
        String id = "801122-2344556";

        if ( id.trim().length() < 14 ||
             id.trim().charAt(6) != '-') {
            System.out.println("주민번호가 잘못됐어요");
        }else{

            String year = id.substring(0,2);
            String month = id.substring(2,4);
            String day = id.substring(4,6);
            char gender = id.charAt(7);

            System.out.printf( "당신은 %s년 %s월 %s일에 태어난 ",year, month, day );
            if ( gender == '1' || gender == '3' ) {
                
                System.out.println( "남자입니다");

            }else{System.out.println("여자입니다");

            }


        }


    }//main
}
