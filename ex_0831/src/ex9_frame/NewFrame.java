package ex9_frame;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class NewFrame extends Frame {

    public NewFrame(Frame f) {

        setBounds(f.getX() + 100, f.getY() + 100, 300, 200);
        setLayout(null);// 자동배치 끄기
        setVisible(true);

        Font font = new Font("", Font.BOLD, 30);
        Label label = new Label("종료할까요?");
        label.setFont(font);
        label.setBounds(50, 50, 200, 30);

        // 예, 아니오 버튼 추가
        Button yesbtn = new Button("예");
        Button nobtn = new Button("아니오");
        yesbtn.setBounds(30, 100, 105, 40);
        nobtn.setBounds(165, 100, 105, 40);

        // 버튼들에게 이벤트 감지자 등록
        yesbtn.addActionListener(act);
        nobtn.addActionListener(act);

        // 현재 프레임에 컴포넌트들 추가
        add(label);
        add(yesbtn);
        add(nobtn);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();// 현재 창 닫기
            }
        });

    }// 생성자

    // 예, 아니오 버튼이 참조할 감지자 생성
    ActionListener act = new ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {

            switch (e.getActionCommand()) {

                case "예":
                    System.exit(0);
                    break;
                
                case "아니오":
                    dispose();
                    break;

            }// switch
        };// actionPerformed
    };
}
