package ex_0812;

public class Ex7_Work {
    public static void main(String[] args) {

        // 0812 숙제
        // 배열 arr을 오름차순으로 출력
        // -------------------------
        // 11 7 9 20 15 3
        // 3 7 9 11 15 20
        int[] arr = { 11, 7, 9, 20, 15, 3 };

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {

            for (int j = i; j < arr.length; j++) {

                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }

            }

        }

        for( int i = 0; i < arr.length; i++ ){
            System.out.print(arr[i] + " ");

        }

    }// main
}
