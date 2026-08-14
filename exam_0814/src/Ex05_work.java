import java.util.Scanner;

public class Ex05_work {
    public static void main(String[] args) {

        // 정수n1, n2를 입력받고
        // n1 ~ n2사이의 합을 출력
        // ------------------------------
        // 수1 : 2
        // 수2 : 5
        // 결과 : 14

        // 수1 : 5
        // 수2 : 2
        // 결과 : 14

        Scanner sc = new Scanner(System.in);
        System.out.print("수1 : ");
        int su1 = sc.nextInt();
        System.out.print("수2 : ");
        int su2 = sc.nextInt();

        int res = 0; //결과 출력용 변수
        int tmp = 0; //su1과 su2 값을 교환
        if (su1 > su2) {
            tmp = su1;
            su1 = su2;
            su2 = tmp;
            
            
        }
        for(int i = su1; i <= su2; i++){
            res += i;
        }

        System.out.println("결과 : " + res);
    }// main
}
