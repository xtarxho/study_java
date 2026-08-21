package ex2_constructor;

public class PokeMain {
    
    public static void main(String[] args) {
        
        //이름과 타입을 반드시 입력해야만 객체가 생성되도록 강제하고 있다
        //필수정보가 빠진 포켓몬이 만들어지는 것을 방지할 수 있다
        Pokemon p1 = new Pokemon("피카츄", "전기"); // 이게 생성자 호출이다
        p1.setName("라이츄");
        p1.info();
        
        //밑에 애들은 빠질 수가 있다
        // p1.setName("피카츄"); 
        // p1.setType("전기");
        // p1.setHp(100);


    }//main

}
