package ex1_file;

import java.io.File;

public class Ex3_File {
    public static void main(String[] args) {
        
        //폴더인지 파일인지 알게해주는 코드 
        String path = "c:/java";
        File f1 = new File(path);

        if (f1.isDirectory()) {
            
            //f1경로의 하위목록을 File구조로 받기
            File[] files = f1.listFiles();

            //files로부터 폴더가 아닌 파일구조의 데이터만 출력
            for( int i = 0; i < files.length; i++ ){

                if (files[i].isFile()) {
                    System.out.println(files[i].getName());
                    
                }

            }
        }

    }//main
}
