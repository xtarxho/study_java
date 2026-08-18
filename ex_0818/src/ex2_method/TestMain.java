package ex2_method;

public class TestMain {
    public static void main(String[] args) {
        
        Test t1 = new Test();
        String res = t1.average(10, 20, 40);    //=형태로 할 수 없다 ->void

        System.out.println("결과 : " + res);
    }//main
}
