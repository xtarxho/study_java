package ex5_buffer_input;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class Ex1_BufferInput {
    public static void main(String[] args) {
        
        //Buffered스트림
        //파일을 읽거나 쓸 때 알아서 버퍼링 작업을 수행하여
        //입출력 속도향상에 도움을 주는 스트림이다 

        String path = "C:/myFile/work3.txt";
        File f = new File(path);
        byte[] result = new byte[(int)f.length()];

        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try{

            fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis); //-> fis가 읽어오는 정보를 빠르게 읽어준다
            bis.read(result);

            String res = new String(result);
            System.out.println(res);

        }catch(Exception e){

        }finally{

            try{
                //생성된 스트림은 반드시
                //만들어진(new) 역순으로 닫아준다
                if (bis != null) { //역순으로 bis먼저 닫아야한다
                    bis.close();

                if (fis != null) {
                    fis.close();
                }

                }

            }catch(Exception e){
                e.printStackTrace();

            }

        }



    }//main
}
