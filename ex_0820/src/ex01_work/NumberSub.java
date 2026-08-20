package ex01_work;

public class NumberSub {
    
    public boolean isNumber(String str){ //String str을 main 클래스에서 받아온다는 것

        for( int i = 0; i < str.length(); i++ ){

            char ch = str.charAt(i); //str에서 i번째 문자 하나라는 뜻

            // 0에 해당하는 아스키 코드는 48이고 9에 해당하는 아스키 코드는 57이다
            if (ch < '0' || ch > '9') {
                return false; // if문의 조건식에 해당안되면 return false를 할 수 없다
            }

        }

        return true; //숫자로 잘 이루어져있으면 ture를 return하게 된다

    }

}
