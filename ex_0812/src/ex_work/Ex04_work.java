package ex_work;

import java.util.Scanner;

public class Ex04_work {
    public static void main(String[] args) {
        
        
        /*
        배열의 크기를 입력하세요: 5
        정수1 : 6
        정수2 : 7
        장수3 : 11
        정수4 : 20
        정수5 : 31
        --------------------
        홀수 개수: 3
        짝수 개수: 2
        */
       //이해 안됨 
       Scanner sc = new Scanner(System.in);
       System.out.print("배열의 크기 : ");
        int size = sc.nextInt();
        
        int[] nums = new int[size];

        int odd = 0; //홀수
        int even = 0; //짝수
        for( int i = 0; i < size; i++ ){
            System.out.printf("정수%d : ", i + 1);
            nums[i] = sc.nextInt();

            if(nums[i] % 2 == 0){
                even++;
            }else{
                odd++;
            }

        }

        System.out.println("짝수 : " + even);
        System.out.println("홀수 : " + odd);

    }// main
}
