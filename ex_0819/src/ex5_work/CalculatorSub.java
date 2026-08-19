package ex5_work;

public class CalculatorSub {
    
    
    public int getResult(int su1, int su2, String op){


        switch(op){

            case "+":
            //System.out.println(su1+su2);
            return su1 + su2;

            case "-":
            // System.out.println(su1-su2);
            return su1 - su2;

            case "*":
            // System.out.println(su1*su2);
            return su1 * su2;

            case "/":
            // System.out.println(su1/su2);
            return su1 / su2;
            
            default :
            System.out.println("연산자를 제대로 입력하세요");
            return -1;

        }
     
    }
}
