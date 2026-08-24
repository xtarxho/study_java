package ex5_abstract;

//추상 클래스
//추상메서드를 하나라도 가지고 있는 클래스
abstract public class AbsParent { 
    
    int value = 100;

    public int getValue(){
        return value;

    }
    //추상메서드 : 메서드의 몸체(body)가 없는
    //미완성 개념의 메서드
    //상속관계에서 자식이 받아서 완성시키는 것을 조건
    //추상화는 몸체(중괄호)를 만들지 않는다
    abstract public void setValue(int n);


}
