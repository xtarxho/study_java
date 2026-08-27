package ex2_fileinput;

public class Ex3_Input {
    public static void main(String[] args) {
        
        //Scanner sc = new Scanner(System.in); -> System.in.read( console );
        //sc.close -> 스캐너를 닫는다

        byte[] console = new byte[100];

        try{
            System.out.print("입력 : ");

            //in은 static이다 
            System.in.read( console ); //System.in -> 키보드에서 값 받을려고하는 입력 스트림

            String res = new String( console ); //생성자 사용
            System.out.println(res.trim());

            System.in.close();

        }catch(Exception e){

        }

    }//main
}
