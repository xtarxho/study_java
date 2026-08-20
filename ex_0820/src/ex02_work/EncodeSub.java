package ex02_work;

public class EncodeSub {

    char[] abcCode = { '`', '~', '!', '@', '#', '$', '%', '^',
            '&', '*', '(', ')', '-', '_', '+', '=', '|',
            '[', ']', '{', '}', ';', ':', ',', '.', '/' };
    
    char[] numCode = { 'q', 'w', 'e', 'r', 't', 'y',
            'u', 'i', 'o', 'p' };

    //String result = "";
    StringBuffer result = new StringBuffer();

    //키보드에서 bc23을 입력받았을 때 

    public String encoding( String str ){

        for( int i = 0; i < str.length(); i++ ){

            char ch = str.charAt(i);

            if ( ch > 'a' && ch < 'z' ) {
                result.append( abcCode[ ch - 'a' ] ); //i가 0일 때 ch는 문자 b를 가지고 있다 b - a = 1 -> 아스키코드로 보면 98 - 97이다

            }else if( ch >= '0' && ch <= '9' ){
                result.append( numCode[ ch - '0' ] );  //i가 2일 때 ch는 2의 값을 가지고 있음 

            }

        }

        return result.toString();

    }

}
