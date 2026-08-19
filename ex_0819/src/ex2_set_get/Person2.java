package ex2_set_get;

public class Person2 {
    
    private String name; //this.name이 가리키는 것이다
    private int age;

    public void setinfo( String name, int age ){
        //this는 현재 내 클래스 자신이라는 뜻이다
        this.name = name;
        this.age = age; 
        
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

}
