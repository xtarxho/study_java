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
        String  res2 = (( age >= 15 || cm >= 150) ? "탑승가능" : "탑승불가" );

        System.out.println(res2);



    }//main
}
