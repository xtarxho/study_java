package ex4_super;

public class Child extends Parent{
    
    public Child(){
        super(10); //현재 자식 클래스의 부모를 의미한다 자동으로 만들어진다 
        System.out.println("자식클래스의 생성자");

    }

    @Override
    public int getNum() {
        super.num = 100; //부모의 num변수로 접근
        return super.getNum(); //부모의 getnum()메서드 호출
        
    }

}
