package ex3_throws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WorkSub2 {
    
    File f = new File(MyPath.PATH + "work.txt");
    byte[] console = new byte[(int)f.length()];
    FileInputStream fis = null;

    //현재 메서드에서 발생할 수 있는 예외를
    //나중에 나를 사용할 곳으로 던져버린다
    public void result() throws FileNotFoundException, IOException{  //throws는 오류를 잡아준다

        fis = new FileInputStream(f); // -> FileNotFoundException 오류 이름
        fis.read(console); // -> IOException 오류 이름

        System.out.println(new String(console));

        fis.close();

    }//result()

}
