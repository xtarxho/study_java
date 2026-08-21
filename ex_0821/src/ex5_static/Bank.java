package ex5_static;

public class Bank {
    
    private String point;
    private String tel;
    static float interest = 10f;

    public Bank( String point, String tel ){
        this.point = point;
        this.tel = tel;

    }

    public void info(){
        System.out.println("지점 : " + point);
        System.out.println("전화 : " + tel);
        System.out.println("이자율 : " + interest + "%");
        System.out.println("------------------");
    }

}
