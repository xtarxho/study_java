package ex7_filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Ex3_BufReader {
    public static void main(String[] args) {
        
        String path = "C:/myFile/work3.txt";
        File f = new File(path);
        
        FileReader fr = null;
        BufferedReader br = null;

        try{

            fr = new FileReader(f);
            br = new BufferedReader(fr); //버퍼드리더는 줄단위로 읽을 수 있다

            String msg;

            //BufferedReader가 줄 단위로 내용을 읽어온다
            while ( (msg = br.readLine()) != null ) {
                System.out.println(msg);

            }//while

        }catch(Exception e){

        }finally{

            try{

                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }

            }catch(Exception e){

            }

        }

    }//main
}
