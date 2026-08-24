package ex9_exception;

public class Ex2_TryCatch {
    public static void main(String[] args) {
        
        int res = 0;
        int[] arr = {1,2,3};

        try{

            res = 10 / 2;
            arr[2] = 100;

        }catch(Exception e){

            e.printStackTrace();
            System.out.println("오류발생");

        }finally{
            //예외 여부와 상관없이 반드시 호출되는 영역
            System.out.println("finally 영역");

        }

        System.out.println("프로그램 정상종료");

    }//main
}
