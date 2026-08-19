package ex1_copyarray;

public class ArrMain {
    public static void main(String[] args) {
        
        //얕은 복사 메인
        int[] array = {1, 2, 3, 4, 5};

        ArrSub as = new ArrSub();
        as.param(array); // param을 호출하면서 array라는 주소를 서브로 보낸다
                         // 주소가 같으므로 array와 arr의 값이 같이 바뀐다

        System.out.println("main : " + array[0]);

        

    }//main
}
