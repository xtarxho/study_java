package ex5_work;

public class Ex1_work {
    public static void main(String[] args) {

        /*
         * 무조건 복습~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * 1번째 문제 - 과수원이 있다.
         * 배, 사과, 오렌지를 키우고 있는데, 하루에 생산되는 양이
         * 각각 5, 7, 5개.
         * 
         * 1. 과수원에서 하루에 생산되는 과일의 총 갯수를 출력
         * 2. 시간당 전체 과일의 평균 생산 갯수
         * 단, 과일의 갯수를 담는 변수는 int, 평균을 계산할 변수는 float
         * /*1번 문제 풀이
         * int pear = 5;
         * int apple = 7;
         * int orange = 5;
         * int total = pear + apple + orange;
         * float avg = total / 24f;
         * //float avg = (float)total / 24; 두가지 방법이 있음 total을 실수로 변경하기 24뒤에 f를 붙이기
         * 
         * System.out.println("하루생산량 : " + total);
         * System.out.println("시간당 평균 : " + avg);
         */

        /*
         * 2번째 문제 - 동물원이 있다.
         * 호랑이, 사자, 곰이 각각 하루에 먹는 고기 양이
         * 각각 8kg, 6kg, 4kg이다.
         * 
         * 1. 동물원에서 하루에 사용되는 총 고기 양을 출력하시오.
         * 2. 시간당 평균 고기 사용량을 출력하시오.
         * 단, 고기 양을 담는 변수는 int, 평균을 계산할 변수는 float를 사용한다.
         */

        int tiger = 8;
        int lion = 6;
        int bear = 4;
        int total = tiger + lion + bear;
        float avg = total / 24f;
        System.out.println("하루고기양 : " + total);
        System.out.println("시간당 평균: " + avg);

        float check = avg * 24;
        System.out.println("확인 : " + check);

        /*
         * 3번째 문제 - 빵집이 있다.
         * 식빵, 크루아상, 머핀의 하루 생산량이 각각 20개, 15개, 10개이다.
         * 
         * 1. 빵집에서 하루에 생산되는 전체 빵의 양을 출력하시오.
         * 2. 분당 평균 빵 생산량을 출력하시오.
         * 단, 각 빵의 생산량은 double형 변수, 평균을 계산하는 변수는 float형 변수를 사용한다.
         * 
         */

        
        /*
         * [응용 연습문제]
         * 
         * 우리 동네 과수원에서는 하루에 배, 사과, 오렌지, 그리고 바나나를 생산하고 있다.
         * 각 과일의 하루 생산량은 각각 8개, 12개, 7개, 10개이다.
         * 
         * 1. 하루에 생산되는 과일의 총 갯수를 변수에 저장하고 출력하시오.
         * 2. 30분(0.5시간)마다 생산되는 전체 과일의 평균 갯수를 구하여 출력하시오.
         * 3. 하루 총 생산량의 절반 이상을 차지하는 과일이 무엇인지(과일 이름) 구해서 출력하시오.
         * 
         * (단, 변수의 자료형은 적절하게 선택한다.)
         */
    }
}
