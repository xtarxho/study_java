package ex7_interface;

//인터페이스는 implements 키워드를 통해 구현한다.
public class InterChild implements InterParent{
    

    //부모인 인터페이스가 가진 추상메서드를 
    //자식이 반드시 가지고 있어야 한다.
    @Override
    public int getValue() {
        return 0;
    }


}
