package ex3_socket;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends Thread{
    
    ServerSocket ss;
    List<CopyClient> list;
    

    public ChatServer(){
        try{

            list = new ArrayList<CopyClient>();
            

            ss = new ServerSocket(3500);
            System.out.println("서버시작");

        }catch(Exception e){

        }
    }//생성자
    @Override
    public void run() {
        
        while (true) {
            
            try{

                //접속자를 기다린다 -> 접속자는 클라이언트
                Socket s = ss.accept();
                String ip = s.getInetAddress().getHostAddress();
                System.out.println(ip + "님 접속");

                //방금 접속한 클라이언트의 복사본을 만들어서
                //리스트에 탑재를 한다
                CopyClient cc = new CopyClient(s,this);
                list.add(cc);
                cc.start();


            }catch(Exception e){

            }

        }//while

    }//run

    //접속한 모든 사용자가 메세지를 받을 수 있도록 메서드를 만든다
    public void sendMessage(String msg){

        try{

            for(CopyClient cc : list){
                cc.out.println(msg);

            }//for

        }catch(Exception e){

        }

    }//sendMessage

    //다른 사용자들에게 종료를 알리고 List에서 클라이언트를 제거하기 위한 메서드
    public void removeClient( CopyClient cc ){

        list.remove(cc);
        sendMessage(cc.ip + "님이 퇴장하셨습니다");

    }

}
