package ex2_set_get;

public class PersonMain {
    public static void main(String[] args) {
    
        Person p1 = new Person();
        p1.setGender("여자"); //setGender를 통해 괄호 안에 있는 값을 서브 setGender s로 보낸다 
        System.out.println("성별 : " + p1.getGender()); //서브에 있는 return gender를 호출한다

        p1.setName("홍길동");
        System.out.println("이름 : " + p1.getName());

        p1.setAge(27);
        System.out.println("나이 : " + p1.getAge());



        System.out.println("-------------------------");

        Person2 p2 = new Person2();
        p2.setinfo("홍길동", 30);

        System.out.println("이름 : " + p2.getName());
        System.out.println("나이 : " + p2.getAge());

    }//main

}
