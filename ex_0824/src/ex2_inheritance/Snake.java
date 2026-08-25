package ex2_inheritance;

public class Snake extends Animal{

    String merit = "밤에도 잘봄";

    //오버라이드 : 메서드의 재 정의
    //상속관계의 객체에서 부모의 함수를 자식이 가져와서 자식 사정에 맞게 내용만 재정의 하는 것
    //부모만 가진 메서드의 껍데기만 가져오고 내용을 자식 사정에 맞게 수정한다 -> override
    
    @Override
    public int getLeg() {
        return 0;
    }

}
