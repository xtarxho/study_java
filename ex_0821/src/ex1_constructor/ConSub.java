package ex1_constructor;

public class ConSub {
    
    int n = 10;

    public ConSub(){ //private을 사용하면 메모리 할당을 받을 수 없게 된다.
        //여태 써오던 메서드와 생성자는 큰 차이가 있다. -> 반환형이 없다. 클래스명과 이름이 반드시 동일해야 한다.
        System.out.println("메모리 할당 해줄게");
    }

}
