package computer;

public class Computer {
    

    //속성, 변수, 멤버...라고 부른다
    private String brand = "samsung";
    int ssd = 512;
    int ram = 256;
    float cpu = 1.8f;
    String color = "white";


    //메서드( 함수 )
    //메서드 : 어떤 작업을 수행하기 위한 명령문들의 집합
    //메서드를 사용하면 반복적인 코드의 재활용이 편해진다
    public void info(){
        System.out.println("브랜드 : " + brand);
        System.out.println("ssd : " + ssd);
        System.out.println("ram : " + ram);
        System.out.println("cpu : " + cpu);
        System.out.println("c색상 : " + color);
        System.out.println("--------------------------");
    }


    //메서드의 구성
    //pubilc -> 접근 제한자
    //void -> 반환명
    //info -> 메서드명
    //메서드의 첫글자 이름은 무조건 소문자로 작성
    //public void info( 파라미터(인자) ){ 메서드의 실행영역 }

    //접근제한자 -> 크게 4종류가 있음
    //1. pubilc : 같은 프로젝트 내의 모든 객체에게 사용을 허가
    //2. private : 현재 클래스에서만 사용을 허가 
    //3. protected : 상속관계의 객체에게만 사용을 허가
    //4. default : 같은 패키지의 객체에게 사용을 허가


}
