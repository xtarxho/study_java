package ex5_rsp;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ScoreLoad {
    
    public User load(User user){
        String path = "C:/myFile/RspGame/"+user.getId()+"/info.sav";
        File dir = new File(path);

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        if (dir.exists()) {
            
            try{

                fis = new FileInputStream(dir);
                ois = new ObjectInputStream(fis);

                user = (User)ois.readObject();

                System.out.println("로드성공");

            }catch(Exception e){
                System.out.println("로드실패");
            }finally{

                try{

                    if(ois != null) ois.close();
                    if(fis != null) fis.close();

                }catch(Exception e){

                }

            }


        }

        return user;

    }//load()

}
