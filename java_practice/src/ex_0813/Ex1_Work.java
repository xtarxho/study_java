package ex_0813;

public class Ex1_Work {
    public static void main(String[] args) {
        
        // 배열 arr에 담긴 모든 값의 합과 평균 구하기
        // ------------------------------
        // 총합 : 119
        // 평균 : 7.43
        int[][] arr = { { 7, 8, 11, 6 },
                        { 20, 13, 9, 2, 5 },
                        { 7, 7, 10 },
                        { 2, 3, 4, 5 } };

        int sum = 0;
        float avg = 0;
        int cnt = 0;
        for( int i = 0; i < arr.length; i++ ){

            for( int j = 0; j < arr[i].length; j++ ){

                sum += arr[i][j];
                cnt++;
            }

        }
        System.out.println(sum);
        System.out.println(cnt);
        System.out.println(avg = (float)sum / cnt);
    }//main
}
