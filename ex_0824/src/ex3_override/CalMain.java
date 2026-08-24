package ex3_override;

public class CalMain {
    public static void main(String[] args) {
        
        CalPlus cp = new CalPlus();
        int n = cp.getResult(10,20); // -> 30
        System.out.println(n);

        CalMinus cm = new CalMinus();
        n = cm.getResult(15,10); // -> 5
        System.out.println(n);

    }//main
}
