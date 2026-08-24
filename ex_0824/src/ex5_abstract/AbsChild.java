package ex5_abstract;

public class AbsChild extends AbsParent{
    
    //추상클래스를 상속받은 자식클래스는
    //부모가 가지고 있는 추상메서드를 무조건 받아둬야 한다.
    //재정의를 안하더라도 오버라이딩을 통해 가지고는 있어야 한다.

    @Override
    public void setValue(int n) {
        n += 100;
    }

}
