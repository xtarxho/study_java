package ex4_static;

public class StaticMain {
    
    int n;
    static String str;

    public void test1(){
        n = 10;
        str = "안녕";
    }

    public static void test2(){
        //static메서드에서는
        //일반 변수의 참조가 불가능하다.
        //n = 10;
        str = "안녕";

        int num = 100;
        
        //static메서드 안에서는
        //static변수를 정의할 수 없다.
        //static int num2 = 200;
        
    }

}
