package ex6_fileoutput;

import java.io.File;
import java.io.FileOutputStream;

public class Ex1_Output {
    public static void main(String[] args) {
        
        File f = new File("C:/myFile/fileOutput.txt");
        FileOutputStream fos = null;

        try{

            fos = new FileOutputStream(f,true); //append true는추가로 이어붙이게 해준다 

            String msg = "\n안녕하세요 반갑습니다";
            fos.write(msg.getBytes()); // -> getBytes는 msg를 바이트로 쪼개준다


        }catch(Exception e){

        }finally{

            try{

                if (fos != null) {
                    fos.close();
                }

            }catch(Exception e){

            }

        }

    }//main
}
