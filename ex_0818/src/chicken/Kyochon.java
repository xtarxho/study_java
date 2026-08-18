package chicken;

public class Kyochon {
    public static void main(String[] args) {
        
        //클래스의 다형성
        //c1과 c2는 같은 클래스에서 만들어졌지만 각각 다른 정보를 가진 서로 다른 객체로써 사용될 수 있다
        // -> 이것을 "클래스의 다형성"이라고 부른다
        Chicken c1 = new Chicken();
        c1.order();

        Chicken c2 = new Chicken();
        c2.name = "양념";
        c2.price = 10000;
        c2.order();


    }//main
}
