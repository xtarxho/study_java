package ex1_array;

public class Ex1_array {
    public static void main(String[] args) {

        // 배열은 '같은 자료형끼리 모아둔 하나의 묶음'
        // 효율적인 자료관리를 위해 반드시 필요하다

        int su1 = 100;
        int su2 = 200;
        int su3 = 300;
        int su4 = 400;

        // 1) 배열 선언 []대괄호가 있으면 배열
        int[] arr;

        // 2) 배열 생성
        arr = new int[6];

        // 배열 초기화
        arr[0] = 100;
        arr[1] = 200;
        arr[2] = 300;
        arr[3] = 400;
        // 존재하지 않는 index에 접근할 수 없다
        arr[4] = 500;

        // 배열의 모든 내용을 출력
        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i]);

        }

    }// main
}
