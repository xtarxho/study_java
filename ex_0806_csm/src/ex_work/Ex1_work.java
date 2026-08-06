package ex_work;

public class Ex1_work {
    public static void main(String[] args) {
        
        //나이가 15세 이상이거나, 키가 150cm이상이면 탑승가능
        //그렇지 않으면 탑슬 불가를 출력

        //내가한거
        int age = 12;
        int cm = 160;
        /*String res = "";

        if( age >= 15 ){
            res = "탑승가능";

        }else if( cm >= 150 ){
            res = "탑승가능";

        }else{
            res = "탑승불가";
        }

        System.out.println(res);


        //쌤이 알려주신거 (훨씬 효율적)
        if(age >= 15 || cm >= 150){
            // || -> or 연산자 사용?
            res = "탑승가능";
        }else{
            res = "탑승불가";
        }

        System.out.println(res);
*/
        //내가 만든 삼항연산자 
        String  res2 = ( age >= 15 || cm >= 150 ) ? "탑승가능" : "탑승불가" ;

        System.out.println(res2);




        // [문제1] 시험 점수가 60점 이상이거나, 과제 점수가 80점 이상이면 "합격"을 출력하고,
        // 둘 다 해당되지 않으면 "불합격"을 출력하는 코드를 작성해보세요.

        int test = 46;
        int homework = 84;
        String str = "";

        if( test >= 60 || homework >=80 ){
            str = "합격";
        }else{
            str = "불합격";
        }

        System.out.println( str );



        // [문제2] 나이가 65세 이상이거나, 장애인등록증이 있으면 "교통비 무료"를 출력,
        // 아니면 "교통비 유료"를 출력하는 코드를 만들어보세요.

        int age1 = 76;
        boolean card = true;
        String ggg = "";

        if( age1 >= 65 || card == false ){
            ggg = "교통비 무료";
        }else{
            ggg = "교통비 유료";
        }

        System.out.println(ggg);




        // [문제3] 회원 등급이 "VIP"이거나, 누적 구매금액이 100만원 이상이면 "할인 적용",
        // 아니면 "할인 없음"을 출력하는 조건문을 작성해보세요.

        String a = "vvip";
        int b = 500000;
        String str2 = "";

        if( a == "vip"  ||  b >= 1000000 ){
            str2 = "할인 적용";
        }else{
            str2 = "할인 없음";
        }


        System.out.println(str2);





    }//main
}
