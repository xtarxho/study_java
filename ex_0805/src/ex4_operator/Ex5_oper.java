package ex4_operator;

public class Ex5_oper {
    public static void main(String[] args) {


        //삼항(조건)연산자
        //하나의 조건식을 통해 발생하는 결과를 원하는 타입으로
        //반환할 수 있도록 하는 연산자 
        int a = 10;
        int b = 15;
        int res = ++a >= b ? 100 : 200; //100은 조건식이 참일때 가져가는 값 200은 조건식이 거짓일때 가져가는 값
        System.out.println("res : " + res);

        int n1 = 10;
        int n2 = 20;
        float res2 = (n1 += n1) == n2 ? 3.14f : 5.19f;
        System.out.println("res2 : " + res2);


        a = 10;
        b = 12;
        char res3 = ++a >= b || (a - 8) + 7 <= b && 13 - b >= 0 && (a+=b) - (a%b) > 10 ? 'O' : 'X'; //++a 였을때 11로 바뀌는 함정, (a+=b)에서 a가 23으로 바뀜, (a%b)는 a가 23이 됨
        // false     //         true && true        &&  true
        System.out.println("res3 : " + res3);
    }// main
}
