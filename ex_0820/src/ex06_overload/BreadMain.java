package ex06_overload;

public class BreadMain {
    public static void main(String[] args) {
        
        //빵을 만들었습니다 <-- 메서드 1 호출했을 때 나와야하는 결과
        //--------------------
        //빵을 만들었습니다
        //빵을 만들었습니다
        //요청하신 2개의 빵을 만들었습니다 <-- 메서드 2 호출했을 때 나와야한다
        //----------------------------
        //크림빵을 만들었습니다
        //크림빵을 만들었습니다
        //요청하신 2개의 크림빵을 만들었습니다 <-- 메서드 3 호출했을 때 나옴

        Bread br = new Bread();
        br.makeBread();
        br.makeBread(4);
        br.makeBread(3,"크림");

        

    }//main
}
