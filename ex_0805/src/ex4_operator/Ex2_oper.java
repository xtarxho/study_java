package ex4_operator;

public class Ex2_oper {
    public static void main(String[] args) {
        
        //대입연산자
        //특정 값을 변수에 전달하여 기억시킬 때 사용하는 연산자
        int n1 = 10; 
        int n2 = 7;
        n2 += n1; //n2 = n2 + n1;
        System.out.println("n2 : " + n2);

        n2 -= 2;// n2 = n2 - 2;
        System.out.println("n2 : "+ n2);

        n1 *= n1; //n1 = n1 * n1;
        System.out.println("n1 : " + n1);

        n1 /= 5; // n1 = n1 / 5;
        System.out.println("n1 : "+ n1);

        n1 %= 3; // n1 = n1 % 3;
        System.out.println("n1 : " + n1);

        //비교 연산자
        //변수나 상수의 값을 비교하여 결과를 만드는 연산자
        int i1 = 10;
        int i2 = 20;
        boolean res = i1 <= i2; // <는 작다 lt, >는 크다 gt
        System.out.println("res : " + res);

        res = i1 == i2;
        System.out.println("res : " + res);

        res = i1 != i2;
        System.out.println("res : " + res);
    }
}
