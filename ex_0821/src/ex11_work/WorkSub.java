package ex11_work;

import java.util.Random;

public class WorkSub {
    
    String[] strArr = { "APPLE", "ORANGE", "HOPE", "VIEW" };
    StringBuffer shake = new StringBuffer();
    Random rnd = new Random();


    //정답을 반환하는 메서드
    public String getAnswer(){
        int idx = rnd.nextInt(strArr.length);
        return strArr[idx];
    }

    public String scrambleWord(String str){
        
        //정답을 문자단위로 끊어서 저장할 배열
        int[] arr = new int[str.length()];

        outer : for( int i = 0; i < arr.length; ){

            arr[i] = rnd.nextInt(str.length());

            for( int j = 0; j < i; j++ ){

                if (arr[i] == arr[j]) {
                    continue outer;
                }

            }

            i++;
        }

        //문제 만들기
        for( int i = 0; i < str.length(); i++ ){

            shake.append(str.charAt(arr[i]));

        }

        return shake.toString();

    }

}
