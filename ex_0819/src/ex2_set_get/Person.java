package ex2_set_get;

public class Person {
    
    private String gender;  //private을 사용하면 여기서는 사용가능한데 다른 곳에서는 사용 불가능하다
    
    public void setGender(String s){ //private을 세팅할 수 있음
        gender = s;
    }

    public String getGender(){
        return gender;
    }

    private String name;
    
    public void setName(String n){
        name = n;
    }

    public String getName(){
        return name;
    }
    
    private int age;

    public void setAge(int a){
        age = a;
    }
    public int getAge(){
        return age;
    }

}
