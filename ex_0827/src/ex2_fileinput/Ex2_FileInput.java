package ex2_fileinput;

import java.io.File;
import java.io.FileInputStream;

public class Ex2_FileInput {
    public static void main(String[] args) {
        
        String path = "C:/myFile/test.txt";
        File f = new File(path);
        byte[] read = new byte[(int)f.length()]; // new byte[100] -> new byte[(int)f.length()]로 수정 
        FileInputStream fis = null;

        if (f.exists()) {
            
            try{
                fis = new FileInputStream(f);
                fis.read(read);
                String res = new String( read );

                System.err.println(res);

            }catch(Exception e){

            }finally{
                try{

                    if ( fis != null ) {
                        fis.close();
                    }

                }catch(Exception e){

                }
            }

        }

    }//main
}
