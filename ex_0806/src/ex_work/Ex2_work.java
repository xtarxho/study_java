package ex_work;

import java.util.Scanner;

public class Ex2_work {
    public static void main(String[] args) {
        
        //비교값으로 1 ~ 12월 사이의 값을 준비
        //준비된 비교값에 해당하는 달이 몇일까지 있는지 출력
        //---------------
        //3월은 31일까지 있습니다

        //내가 풀어본거
        int month = 5;
        

        switch( month ){
            
            case 1:
                System.out.println("1월은 31일까지 있습니다");
                break;

            case 2:
                System.out.println("2월은 28일까지 있습니다");
                break;

            case 3:
                System.out.println("3월은 31일까지 있습니다");
                break;

            case 4:
                System.out.println("4월은 30일까지 있습니다");
                break;

            case 5:
                System.out.println("5월은 31일까지 있습니다");
                break;

            case 6:
                System.out.println("6월은 30일까지 있습니다");
                break;
            
            case 7:
                System.out.println("7월은 31일까지 있습니다");
                break;

            case 8:
                System.out.println("8월은 31일까지 있습니다");
                break;

            case 9:
                System.out.println("9월은 30일까지 있습니다");
                break;

            case 10:
                System.out.println("10월은 31일까지 있습니다");
                break;

            case 11:
                System.out.println("11월은 30일까지 있습니다");
                break;

            case 12:
                System.out.println("12월은 31일까지 있습니다");
                break;
        }
        

        //비교값으로 1 ~ 12월 사이의 값을 키보드에서 입력
        //준비된 비교값에 해당하는 달이 몇일까지 있는지 출력
        //---------------
        //3월은 31일까지 있습니다



        //쌤이 알려주신 방법 개쩌는 방법
        
        Scanner sc = new Scanner(System.in);

        System.out.print( "월 : " );
        int month2 = sc.nextInt();
        
            switch( month2 ){

                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    System.out.println(month2 + "월은 31일까지 있습니다");
                    break;

                case 4:
                case 6:
                case 9:
                case 11:
                    System.out.println(month2 + "월은 30일까지 있습니다");
                    break;

                case 2:
                    System.out.println("2월은 28일까지 있습니다");

                default:
                    System.out.println(month2 + "월은 없습니다");
                    break;

            }

        //비교값으로 1 ~ 12월 사이의 값을 키보드에서 입력
        //준비된 비교값에 해당하는 달이 몇일까지 있는지 출력
        //---------------
        //3월은 31일까지 있습니다



        



    }//main
}
