package ex3_form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class FormMain {
    public static void main(String[] args) {

        Frame f = new Frame();
        f.setBounds(500, 200, 400, 600);
        f.setBackground(Color.CYAN);
        f.setLayout(null);

        Font font = new Font("", Font.PLAIN, 30);

        // 최상단에 들어갈 내용을 준비
        TextField tf = new TextField();
        tf.setFont(font);
        tf.setBounds(10, 30, 280, 35);

        Button btn = new Button("입력");
        btn.setEnabled(false);// 버튼 비활성화
        btn.setBounds(300, 30, 80, 35);

        // 중앙단
        TextArea ta = new TextArea();
        ta.setFont(font);
        ta.setBounds(10, 70, 380, 450);
        ta.setEditable(false);// ta에 직접 값을 입력할 수 없다

        // 최하단
        Button btnSave = new Button("저장");
        Button btnClose = new Button("종료");
        btnSave.setBounds(45, 530, 150, 50);
        btnClose.setBounds(205, 530, 150, 50);

        // tf에 값이 들어간 경우를 감지
        tf.addTextListener(new TextListener() {
            @Override
            public void textValueChanged(TextEvent e) {

                if (tf.getText().trim().equals("")) {
                    //버튼 클릭 비활성화
                    btn.setEnabled(false);
                }else{
                    //버튼 클릭 활성화
                    btn.setEnabled(true);
                }

            }
        });

        //입력버튼에 클릭을 감지
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.append(tf.getText() + "\n");
                tf.setText("");
                tf.requestFocus();
            }
        });

        //tf에서 키보의 엔터값을 감지
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    ta.append(tf.getText() + "\n");
                    tf.setText("");
                    tf.requestFocus();
                }
            }
        });

        //종료버튼 클릭 감지
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //저장버튼 클릭 감지
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String msg = ta.getText();

                FileWriter fw = null;
                BufferedWriter bw = null;

                try{

                    FileDialog fd = new FileDialog(f,"저장",FileDialog.SAVE);
                    fd.setVisible(true);

                    String path = fd.getDirectory() + fd.getFile();
                    System.out.println(path);

                    //msg를 저장
                    fw = new FileWriter(path);
                    bw = new BufferedWriter(fw);
                    bw.write(msg);

                    bw.flush();

                }catch(Exception e2){

                }finally{

                    try{

                        if (bw != null) bw.close();
                        if (fw != null) fw.close();

                    }catch(Exception e3){

                    }

                }

            }
        });

        f.add(tf);
        f.add(btn);
        f.add(ta);
        f.add(btnSave);
        f.add(btnClose);

        // 강제로 프레임 크기 조정하지 못하도록 설정
        f.setResizable(false);

        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            };
        });
    }// main
}
