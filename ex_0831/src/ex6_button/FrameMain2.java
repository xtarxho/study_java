package ex6_button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class FrameMain2 {
    public static void main(String[] args) {

        Frame f = new Frame();
        f.setLayout(null); // 자동배치를 끈다
        f.setBounds(500, 100, 800, 500);

        // 버튼들 생성
        Button btn1 = new Button("버튼1");
        Button btn2 = new Button("버튼2");
        Button btn3 = new Button("버튼3");
        Button btn4 = new Button("버튼4");

        // 버튼들의 좌표, 크기 지정
        btn1.setBounds(100, 150, 100, 200);
        btn2.setBounds(250, 150, 100, 200);
        btn3.setBounds(400, 150, 100, 200);
        btn4.setBounds(550, 150, 100, 200);

        // 생성된 버튼의 클릭을 감지할 감지자
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // System.out.println(e.getActionCommand());
                switch (e.getActionCommand()) {
                    case "버튼1":
                        System.out.println("1을 클릭함");
                        break;

                    case "버튼2":
                        System.exit(0);
                        break;
                    case "버튼3":
                        new NewFrame(f);
                        break;
                    case "버튼4":
                        System.exit(0);
                        break;

                }// switch

            }
        };

        btn1.addActionListener(action);
        btn2.addActionListener(action);
        btn3.addActionListener(action);
        btn4.addActionListener(action);

        // 생성된 버튼들을 f에 추가
        f.add(btn1);
        f.add(btn2);
        f.add(btn3);
        f.add(btn4);

        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            };

        });

    }// main
}
