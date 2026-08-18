package ex1_method;

public class MethodTest {
    
    public void test1(){

        System.out.println("test1 메서드 호출");

    }

    public int test2( int n ){
        n += 100;
        System.out.println("결과 : " + n);
        
        //나를 호출한 곳으로 return값을 딱 한개만 가지고 돌아갈 수 있다
        return n;
        //System.out.println("aasdd"); return 밑에 사용할 수 없다
    }   

}
