package ex3_casting;

public class Ex2_casting {
    public static void main(String[] args) {
        
        //2. 디모션
        // - 작은 자료형에 큰 자료형이 대입되는 것(자동 X)
        char c1 = 'D'; //2byte
        int n1 = c1 + 1;//4byte

        c1 = (char)n1;
        System.out.println("c1 : " + c1);

        float f1 = 5.5F; // 4.x
        int n2 = 0;//4byte
        n2 = (int)f1;
        System.out.println("n2 : " + n2);
    }
    
}
