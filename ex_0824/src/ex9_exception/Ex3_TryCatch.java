package ex9_exception;

public class Ex3_TryCatch {
    public static void main(String[] args) {
        
        int[] arr = {10,20,30};
        for( int i = 0; i <= arr.length; i++ ){ // <= 는 오류가 난다 이유는 arr.length는 3인데 i는 0이라서 오류남.
            
            System.out.println(arr[i]);
        }
        System.out.println("프로그램 종료");

    }//main
}
