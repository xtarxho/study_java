package ex3_socket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//상속받고 있어도 implements로 구현 가능
public class ChatClient extends Frame implements Runnable {

    // ui를 위한 객체
    TextArea area;
    TextField input;
    Button send_bt;

    // 서버접속 및 데이터 전달용 객체
    Socket s;
    BufferedReader in;
    PrintWriter out;
    Thread t;

    public ChatClient() {

        setBounds(500, 200, 400, 500);
        setLayout(null);

        area = new TextArea();
        area.setBounds(10, 30, 380, 400);

        input = new TextField();
        input.setBounds(10, 450, 300, 20);

        send_bt = new Button("전송");
        send_bt.setBounds(320, 440, 70, 40);

        add(area);
        add(input);
        add(send_bt);

        //전송버튼 클릭감지
        send_bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData(); //서버로 메세지 전달
            }
        });

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                
                //종료하기 전에 서버에게 접속해제요청
                out.println(Util.ENDTOKEN);

            };
        });

        // 서버접속메서드
        connected();

        // 전달된 메세지를 감지하기 위한 스레드를 실행
        t = new Thread(this);
        t.start();

    }// 생성자

    // 서버접속을 위한 메서드
    private void connected() {
        try {

            s = new Socket("192.168.0.46", 3500);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            out = new PrintWriter(s.getOutputStream(), true);

        } catch (Exception e) {

        }
    }

    //메세지 전송메서드
    private void sendData(){

        String msg = input.getText().trim();
        if (msg.length() > 0) {
            out.println(msg);
        }//if
        
        input.setText("");

    }

    @Override
    public void run() {

        // 전달받은 메세지를 기다렸다가 화면에 출력
        while (true) {
            try {

                String msg = in.readLine();

                if (msg.equals(Util.ENDTOKEN)) {
                    break;
                }

                if (msg != null) {
                    area.append(msg + "\n");
                }

            } catch (Exception e) {

            }
        } // while

        // 열려있는 스트림들 닫기
        try {

            if (out != null)
                out.close();
            if (in != null)
                in.close();
            if (s != null)
                s.close();

        } catch (Exception e) {

        }

        System.exit(0);

    }
}
