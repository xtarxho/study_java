package ex2_fileinput;

import java.io.File;
import java.io.FileInputStream;

public class Ex1_FileInput {
    public static void main(String[] args) {

        String path = "C:/myFile/test.txt";
        File f = new File(path);

        //오류
        // if (f.exists()) {
        //     try {
        //         // 파일과 연결된 입력스트림 -> 읽고 쓰기위한 통로
        //         FileInputStream fis = new FileInputStream(f); // FileInputStream -> 1번에 1byte씩만 가져올 수 있다

        //         int code = 0;

        //         // 스트림은 더이상 읽을 것이 없다면 파일의 끝(EOF)인
        //         // -1을 반환하게 되어있다
        //         // 한글은 2byte 문자이기 때문에 한글을 읽는데 문제가 생긴다
        //         while ((code = fis.read()) != -1) {
        //             System.out.print((char) code);
        //         } // while

        //         // 사용을 마친 스트림은 반드시 닫아줘야 한다 ->매우 중요
        //         fis.close();

        //     } catch (Exception e) {

        //     }

        // }





        // 수정본

        // 전역변수로 뺀다
        FileInputStream fis = null;
        if (f.exists()) {
            try {
                // 파일과 연결된 입력스트림 -> 읽고 쓰기위한 통로
                fis = new FileInputStream(f); // FileInputStream -> 1번에 1byte씩만 가져올 수 있다

                int code = 0;

                // 스트림은 더이상 읽을 것이 없다면 파일의 끝(EOF)인
                // -1을 반환하게 되어있다
                // 한글은 2byte 문자이기 때문에 한글을 읽는데 문제가 생긴다
                while ((code = fis.read()) != -1) {
                    System.out.print((char) code);
                } // while

            } catch (Exception e) {

            }finally{

                try{
                    //사용을 마친 스트림은 반드시 닫아줘야 한다 ->매우 중요
                    fis.close();
                    
                }catch(Exception e){
                    
                }
            }

        }

    }// main
}
