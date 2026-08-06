package ex4_operator;

public class Ex4_oper {
    public static void main(String[] args) {

        // 증감연산자
        // 1씩 증가시키거나 1씩 감소시키는 연산자
        // 선행증감과 후행증감의 차이를 알고있어야 한다
        int a = 10;
        System.out.println("a : " + ++a); // 선행증감은 값을 증가시킨 후 출력한다

        int b = 10;
        System.out.println("b : " + b++); // 후행증감은 값을 출력한 후 증가시킨다 + 다음에 사용할때 증가된 값을 사용한다
        System.out.println(b);

        b++; // 11
        ++b; // 13
        --b; // 12
        b--; // 11
        --b; // 10
        b++; // 11
        System.out.println(++b); // 12
    }// main
}
