package ex3_stringbuffer;

public class Ex1_StringBuffer {
    public static void main(String[] args) {
        
        String str ="안녕";
        str = "반갑습니다";
        str += "!";
        System.out.println(str);

        StringBuffer sb = new StringBuffer("안녕");
        sb.setLength(0); //길이를 줄이면서 안에 있는 내용을 전부 날린다
        sb.append("반가워요");
        sb.append("!!!"); //이어서 붙여주는 역할을 한다
        System.out.println(sb.toString());


    }//main
}
