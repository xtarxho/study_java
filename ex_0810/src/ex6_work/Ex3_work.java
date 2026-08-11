package ex6_work;

import java.util.Scanner;

public class Ex3_work {
    public static void main(String[] args) {
        
        //주사위 두 개를 던졌을 때
        //합이 5, 6, 7이 되는 경우의 수를 찾아 출력
        //--------------------------
        //5 : 1 4  2 3  3 2  4 1
        //6 : 1 5  2 4  3 3  4 2  5 1

     
        //틀림 집가서 복습
        for( int i = 1; i <= 6; i++ ){

            for( int j = 1; j <= 6; j++ ){

                if (i + j == 5) {
                    System.out.print("5 : " + i + j);
                    
                }else if( i + j == 6 ){
                    System.out.print( "6 :  " + i + j);
                }else if( i + j == 7 ){
                    System.out.println( "7 :  " + i + j);
                }



            }

            
            
        }
        System.out.println("================================");

        
        
        String five = "";
        String six = "";
        String seven = "";

        for( int i = 1; i <= 6; i++){

            for( int j = 1; j <= 6; j++ ){

                int res = i + j;

                switch (res) {
                    case 5:
                        five += i + " " + j + "  ";
                        break;
                    case 6:
                        six += i + " " + j + "  ";
                        break;
                    case 7:
                        seven += i + " " + j + "  ";
                        break;
                    
                }

            }

            
        }
        System.out.println( "5 : " + five);
        System.out.println( "6 : " + six);
        System.out.println( "7 : " + seven);




    }//main
}
