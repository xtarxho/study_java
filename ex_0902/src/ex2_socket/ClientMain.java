package ex2_socket;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = null;
        Socket s = null;

        try{
            s = new Socket("192.168.0.21", 3000);

            while (true) {
                
                System.out.print("입력 : ");
                String msg = sc.nextLine(); //공백포함 여러글자 받을 수 있음

                //키보드에서 받은 문자열을 서버로 보낸다
                pw = new PrintWriter(s.getOutputStream());
                pw.println(msg);

                pw.flush();


            }//while

        }catch(Exception e){

        }finally{

            try{

                if(pw != null) pw.close();
                if(s != null) s.close();

            }catch(Exception e){

            }

        }

    }//main
}
