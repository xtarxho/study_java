package ex7_work;

public class WorkSub {

    public int getBigger(int[] arr) {

        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > max) {
                max = arr[i];

            }

        }//for

        return max;

    }

}
