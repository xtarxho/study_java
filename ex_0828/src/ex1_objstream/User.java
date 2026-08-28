package ex1_objstream;

import java.io.Serializable;

public class User implements Serializable { // Serializable 인터페이스 구현만 해놓으면 끝
    // 특정 클래스를 ObjectStream으로 기록하거나 읽어오려면
    // 객체의 직렬화가 필수이다
    // 메모리에 흩어져있던 멤버들을 새로운 메모리 영역에 일렬로 만들어서
    // 복사해두는 작업을 객체의 직렬화 라고 한다

    private String name;
    private int age;
    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
