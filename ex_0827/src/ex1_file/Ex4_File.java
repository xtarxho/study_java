package ex1_file;

import java.io.File;

public class Ex4_File {
    public static void main(String[] args) {
        
        //폴더를 만들 수 있는 코드
        String path = "c:/myFile/abc";
        File f = new File(path);

        //exists() -> f가 참조하고 있는 경로가 물리적으로 존재할 때
        //!f.exists() -> f가 참조하고 있는 경로가 물리적으로 존재하지 않을 때
        if ( !f.exists() ) {
            System.out.println("폴더를 생성합니다");
            //mkdir은 폴더를 하나밖에 못만든다
            f.mkdirs(); //mkdirs를 사용해야 여러개의 폴더도 직접 생성 가능하다
        }

    }//main
}
