package ex7_interface;

public interface InterParent {
    //인터페이스의 구성

    //접근제한자             인터페이스명
    //public    interface  InterParent
    //상수 아니면 추상메서드만 만들 수 있다.
    //상수 -> 

    final int VALUE = 100; //final 키워드를 사용해서 상수 표시
    abstract int getValue();
}
