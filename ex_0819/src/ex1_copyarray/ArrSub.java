package ex1_copyarray;

public class ArrSub {
    
    //얕은 복사 서브
    public void param( int[] arr ){

        arr[0] = 100;
        System.out.println("sub : " + arr[0]);

    }

}
