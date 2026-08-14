import java.util.Scanner;

public class Ex03_work {
    public static void main(String[] args) {
        
        // 수1 : 10
        // 수2 : 20
        // 연산자 : +
        // 10 + 20 = 30

        Scanner sc = new Scanner(System.in);
        System.out.print( "수1 : " );
        int su1 = sc.nextInt();
        System.out.print( "수2 : " );
        int su2 = sc.nextInt();
        System.out.print( "연산자 : " );
        String m = sc.next();


        switch( m ){
            case "+":
                System.out.print( su1 + " + " + su2 + " = " + (su1+su2) );
                break;
            case "-":
                System.out.print( su1 + " - " + su2 + " = " + (su1-su2) );
                break;
            case "*":
                System.out.print( su1 + " * " + su2 + " = " + (su1*su2) );
                break;
            case "/":
                System.out.print( su1 + " / " + su2 + " = " + (float)su1/su2 );
                break;

            default:
                System.out.println("연산자 오류");
                break;
        }   







    }//main
}
