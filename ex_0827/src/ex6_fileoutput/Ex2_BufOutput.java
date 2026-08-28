package ex6_fileoutput;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Ex2_BufOutput {
    public static void main(String[] args) {
        
        String path = "C:/myFile/bufOutput.txt";
        File f = new File(path);

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try{

            fos = new FileOutputStream(f,true);
            bos = new BufferedOutputStream(fos);

            String msg = "\n안녕하세요 반가워요 abcd";

            bos.write(msg.getBytes());

            // flush() -> 기록하고자 하는 값을 물리적으로 저장하는 메서드이다
            bos.flush();

        }catch(Exception e){


        }finally{
            
            try{
                
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }


            }catch(Exception e){
                e.printStackTrace();
            }

        }


    }//main
}
