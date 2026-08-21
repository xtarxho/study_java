package ex2_constructor;

public class Pokemon {

    private String name;  //private은 setter를 통해 값을 변경할 수 있다
    private int hp;
    private String type;

    public Pokemon( String name, String type ){  // 이게 생성자이다 -> 반환형 없음. 괄호 안에 파라미터를 넣었다.
        this.name = name;
        this.type = type;
        hp =100;
    }

    public void setName(String name) {  //이게 setter이다
        this.name = name;
    }

    public void setHp(int hp) {         //이게 setter이다
        this.hp = hp;
    }

    

    public void info() {
        System.out.println("이름 : " + name);
        System.out.println("타입 : " + type);
        System.out.println("체력 : " + hp);
        System.out.println("-------------------");
    }

}
