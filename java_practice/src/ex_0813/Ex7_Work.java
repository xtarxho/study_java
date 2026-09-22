package ex_0813;

import java.util.Scanner;

public class Ex7_Work {
    public static void main(String[] args) {

        // 숙제
        // 홀수 마방진 만들기
        // 홀수 : 3
        // 08 01 06
        // 03 05 07
        // 04 09 02

        Scanner sc = new Scanner(System.in);

        System.out.print("홀수 : ");
        int size = sc.nextInt();

        int[][] square = new int[size][size];

        int num = 1;
        int y = 0;
        int x = 0;

        x = size / 2;

        while (num <= size*size) {
            square[y][x] = num;

            if (num % size == 0) {
                y++;
            }else{
                y--;
                x++;
            }

            if(y < 0)
                y = size - 1;
            if(x >= size)
                x = 0;
            num++;
        }

        for( int i = 0; i < size; i++ ){

            for( int j = 0; j < size; j++ ){

                System.out.printf("%02d ", square[i][j]);
            }
            System.out.println();
        }

    }// main
}
