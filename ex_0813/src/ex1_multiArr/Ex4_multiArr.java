package ex1_multiArr;

public class Ex4_multiArr {
    public static void main(String[] args) {
        
        //많이 사용하진 않지만 이런 방법도 있다 
        char[][] cArr = new char[2][];
        cArr[0] = new char[2];
        cArr[1] = new char[3];

        cArr[0][0] = '안';
        cArr[0][1] = '녕';

        cArr[1][0] = '하';
        cArr[1][1] = '세';
        cArr[1][2] = '요';

        for( int i = 0; i < cArr.length; i++ ){

            for( int j = 0; j < cArr[i].length; j++ ){

                System.out.print(cArr[i][j]);

            }
            System.out.println();

        }

        //안녕
        //하세요



    }//main
}
