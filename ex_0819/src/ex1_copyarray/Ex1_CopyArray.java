package ex1_copyarray;

public class Ex1_CopyArray {
    public static void main(String[] args) {
        
        //얕은 복사 -> 주소를 넘겨준다 그러므로 원본까지 값이 바뀔 수 있다
        int[] arr1 = {1, 2, 3};
        int[] arr2 = arr1;
        arr2[0] = 100;

        System.out.println(arr1[0]); //손을 안댄 arr1도 같이 바뀐다
        System.out.println(arr2[0]);

        //깊은 복사 -> 새로운 주소에 값을 넘겨주는 것으므로 원본 값이 바뀌지 않는다
        int[] arr3 = {1, 2, 3};
        //밑에는 새로운 arr3.length 짜리의 크기를 만드는 방법이다 -> 배열이 3개
        int[] arr4 = new int[arr3.length]; //메모리할당을 따로 받아놓는 것. 

        for( int i = 0; i < arr3.length; i++ ){
            arr4[i] = arr3[i];
        }
        arr3[0] = 100;

        System.out.println(arr3[0]);
        System.out.println(arr4[0]);


        
    }//main
}
