package ex6_work;

public class GradeSub {
    
    public int myPoint( String grade, int n ){

        if (grade.equalsIgnoreCase("vip")) {
            return n * 10/100;

        }else if (grade.equalsIgnoreCase("gold")) {
            return n * 5/100;

        }else if (grade.equalsIgnoreCase("sliver")) {
            return n * 3/100;

        }else{
            System.out.println("회원등급 올바르지 않음");
            return 0;
        }

    }



}
