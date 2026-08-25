package ex7_generic;

public class GenMain {
    public static void main(String[] args) {
        
        //제네릭 타입이라고 부른다
        GenTest<String> g1 = new GenTest<String>(); //g1은 String 타입을 관리한다
        g1.setValue("안녕"); // -> String 타입만 넣을 수 있다
        String res = g1.getValue(); // -> 반환받을 때도 String타입으로 반환 받아야한다
        System.out.println("g1 : " + res);

        //Wrapper 클래스 배웠던거 확인하기
        //제네릭 타입은 기본 자료형을 쓰지 못하고 클래스 타입으로 집어넣어야한다
        GenTest<Integer> g2 = new GenTest<>(); //제네릭 타입을 정수형태로 줬다
        g2.setValue(100);
        int res2 = g2.getValue();
        System.out.println("g2 : " + res2);
    
    }//main
}
