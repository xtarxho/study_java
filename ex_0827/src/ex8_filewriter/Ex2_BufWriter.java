package ex8_filewriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Ex2_BufWriter {
    public static void main(String[] args) {
        
        String path = "C:/myFile/bufferedWriter예제.txt";
        File f = new File(path);
        FileWriter fw = null;
        BufferedWriter bw = null;

        try{

            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);

            for( int i = 0; i < 3; i++ ){

                bw.write(i + "번째 라인");
                bw.newLine(); //한줄 아래로 이동하는 메서드

            }//for

            bw.flush();

        }catch(Exception e){

        }finally{

            try{

                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }

            }catch(Exception e){

            }

        }

    }//main
}
