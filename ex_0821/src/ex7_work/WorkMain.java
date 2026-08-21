package ex7_work;

public class WorkMain {
    public static void main(String[] args) {

        // 가장 큰 값 : 20 찾기
        int[] arr = { 1, 11, 7, 20, 13, 15 };

        WorkSub ws = new WorkSub();
        int res = ws.getBigger(arr);

        System.out.println("가장 큰 값 : " + res);

        // 내가 푼 방법 클래스 하나로 풀었음
        // out: for (int i = 0; i < arr.length; i++) {

        //     for (int j = 0; j < arr.length; j++) {
        //         if (arr[i] < arr[j]) {
        //             int tmp = arr[i];
        //             arr[i] = arr[j];
        //             arr[j] = tmp;
        //             continue out;
        //         }
        //     }
        //     System.out.println("가장 큰 값 : " + arr[i]);
        // }

    }// main
}
