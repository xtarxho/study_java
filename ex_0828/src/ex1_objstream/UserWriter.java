package ex1_objstream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class UserWriter {

    FileOutputStream fos = null;
    ObjectOutputStream oos = null;

    public void writeInfo(User user) { //정보가 담긴 상태를 넘겼다

        String path = "C:/myFile/User/" + user.getTel() + "/save.sav";
        File dir1 = new File("C:/myFile/User/");

        // dir1이 물리적으로 경로를 가지고 있지 않습니까? 라는 뜻
        if (!dir1.exists()) {
            dir1.mkdirs();
        }

        File dir2 = new File(dir1, user.getTel());

        if (!dir2.exists()) {
            dir2.mkdirs();
        }
        try {

            fos = new FileOutputStream(path); //1byte단위로 path로 내보낸다
            oos = new ObjectOutputStream(fos);

            // user객체를 통째로 저장하기 위한
            oos.writeObject(user);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("유저정보 저장 실패");
        } finally {

            try {

                if (oos != null)
                    oos.close();
                if (fos != null)
                    fos.close();

            } catch (Exception e) {

            }

        }

    }

}
