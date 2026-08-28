package ex7_filereader;

import java.io.File;
import java.io.FileReader;

public class Ex1_FileReader {
    public static void main(String[] args) {
        
        String path = "C:/myFile/test.txt";
        File f = new File(path);

        //최대 2byte까지 읽어올 수 있는 char기반의 스트림
        FileReader fr = null;
        try{

            fr = new FileReader(f); 
    
            int code = 0;
    
            while((code = fr.read()) != -1){
    
                System.out.print((char)code);
    
            }//while

        }catch(Exception e){

        }finally{

            try{

                if (fr != null) {
                    fr.close();
                }

            }catch(Exception e){


            }

        }
        

    }//main
}
