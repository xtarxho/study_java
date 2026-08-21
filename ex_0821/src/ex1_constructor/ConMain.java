package ex1_constructor;

public class ConMain {
    public static void main(String[] args) {
        
        //생성자 :
        //객체가 생성될 때 메모리 할당을 위해 딱 한번 반드시 호출되는 것 (집을 지어준다)
        //ConSub cs = new ConSub(); //new는 빈자리가 있는지 찾아주는 역할을 한다
        //cs.ConSub() 다시 호출은 절대 불가능하다

        
        ConSub[] cs = new ConSub[2];
        cs[0] = new ConSub(); //생성자 호출(ConSub())을 해야 메모리 할당을 할 수 있다.
        cs[1] = new ConSub();
        

        


    }//main
}
