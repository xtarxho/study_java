package ex4_work;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ex1_WorkMain {
    public static void main(String[] args) throws IOException{

        // 안녕하세용
        // 저는 홍길동이에용
        // 만나서 반가워용 홍홍홍~~!!!
        // hello my name is hong gil dong -> 텍스트파일에 저장하고 사용

        // C:/myfile/work.txt에서
        // 사용자가 입력받은 값의 출현 빈도를 출력
        // ----------------------
        // 입력 : 홍
        // 홍의 출현 횟수 : 4

        Scanner sc = new Scanner(System.in);
        String path = "C:/myFile/work3.txt";
        File f = new File(path);
        byte[] read = new byte[(int) f.length()];
        FileInputStream fis = null;

        int count = 0;

        try {

            fis = new FileInputStream(f);
            fis.read(read);
            String content = new String(read);

            System.out.print("입력 : ");
            String input = sc.next();

            char s = input.charAt(0);

            for (int i = 0; i < content.length(); i++) {

                if (s == content.charAt(i)) {

                    count++;
                }
            }

            System.out.printf("%c의 갯수 : %d개\n", s, count);

        } catch (Exception e) {
            e.printStackTrace(); //어느시점에서 오류가 나는지 알려주는 기능
        }finally{

            if (fis != null) {
                fis.close();
            }

        }

    }// main
}
