package ex3_constructor;

public class Pen {
    
    //setter랑 getter가 없으면 아무것도 못하는 상태
    private String name;
    private int price;
    private String color;
    private boolean limited;

    public Pen(  ){
        //limited를 파라미터로 받는 생성자 호출
        this(false); //내 자신의 생성자를 호출하는 기능이다 boolean limited를 호출
    }
    
    //한정판 생서을 위한 오버로딩된 생성자이다.
    public Pen( boolean limited ){ //생성자의 오버로드이다.
        this.limited = limited;
        
        if ( limited ) { //limited가 참이냐는 것과 동일하다.
            name = "모나미153-G";
            price = 20000;
            color = "금색";
            
        }else{
            name = "모나미153";
            price = 500;
            color = "흰색";

        }

    }
    public void info(){
        System.out.println("이름 : " + name);
        System.out.println("가격 : " + price);

        if (!limited) { //한정판이 아니라는 뜻
            System.out.println("일반판 / " + color );
        }else{
            System.out.println("한정판 / " + color);
        }
            
        System.out.println("----------------" );


    }

}
