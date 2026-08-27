package ex1_file;

import java.io.File;

public class Ex2_File {
    public static void main(String[] args) {
        String path = "c:/java";
        File f = new File(path);

        //폴더 부르는 방법 -> 이름만 출력
        if (f.isDirectory()) { //!f.isFile()

            //path경로의 하위 요소들의 이름을 가져온다 ->파일인지 폴더인지 모른다
            String[] names = f.list();

            //하위목록들의 이름을 출력
            for( int i = 0; i < names.length; i++ ){
                System.out.println( names[i] );

            }

        }

    }// main
}
