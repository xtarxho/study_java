package ex3_casting;

public class Ex1_casting {
    public static void main(String[] args) {
        //casting(형변환)
        //1. 프로모션
        // - 큰 자료형에 작은 자료형을 대입하는 것(자동)
        double d1 = 100.5; //8byte
        int n1 = 200; //4byte
        d1 = n1;   //대입
        System.out.println("d1 : " + d1);

        

        int n2 = 100; //4byte
        char ch ='A'; //2byte
        n2 = ch;
        System.out.println("n2 : " + n2);
    }
}
