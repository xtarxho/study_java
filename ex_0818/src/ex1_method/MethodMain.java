package ex1_method;

public class MethodMain {
    public static void main(String[] args) {
        
        MethodTest m1 = new MethodTest();
        m1.test1();

        int su = 100;
        int res = m1.test2(su); //인자 파라미터는 원본을에는 영향을 주지 않는다 -> 복사본 활용

        System.out.println("su : " + res);

    }//main
}
