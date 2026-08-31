package ex7_Frame;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;



public class FrameMain {
    public static void main(String[] args) {

        Frame f = new Frame();
        f.setLayout(null);// 자동배치 끄기
        f.setBounds(500, 100, 700, 300);

        Font font = new Font("궁서체", Font.PLAIN, 30);

        // 질문1
        Label q1 = new Label("1.관심분야는 무엇입니까?");
        q1.setFont(font);
        q1.setBackground(Color.YELLOW);
        q1.setBounds(50, 30, 400, 30);

        // checkbox생성
        Checkbox news = new Checkbox("뉴스");
        news.setBounds(50, 65, 100, 30);

        Checkbox sports = new Checkbox("운동");
        sports.setBounds(160, 65, 100, 30);

        // 체크박스의 선택 여부 판단
        ItemListener item = new ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {

                String cb = (String) e.getItem();
                String res = "";

                switch (cb) {
                    case "뉴스":
                        res = e.getStateChange() == 1 ? "뉴스 선택" : "뉴스 선택 해제";
                        break;

                    case "운동":
                        res = e.getStateChange() == 1 ? "운동 선택" : "운동 선택 해제";
                        break;
                }// switch

                System.out.println(res);

            }

        };

        news.addItemListener(item);
        sports.addItemListener(item);

        // 질문2
        Label q2 = new Label("메일 수신 여부");
        q2.setFont(font);
        q2.setBounds(50, 100, 400, 30);

        // 라디오버튼
        CheckboxGroup group = new CheckboxGroup();
        Checkbox yes = new Checkbox("y", group, true);
        Checkbox no = new Checkbox("n", group, false);

        yes.setBounds(50, 135, 100, 30);
        no.setBounds(160, 135, 100, 30);

        // 라디오버튼에 이벤트 감지자 만들기
        ItemListener itemRadio = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println((String) e.getItem());

                switch ((String) e.getItem()) {
                    case "y":
                        System.out.println("메일수신함");
                        break;
                    case "n":
                        System.out.println("메일수신 안함");
                        break;

                }// switch

            }
        };

        yes.addItemListener(itemRadio);
        no.addItemListener(itemRadio);

        // 프레임에 각종 컴포넌트를 추가할 수 있음
        f.add(q1);
        f.add(news);
        f.add(sports);
        f.add(q2);
        f.add(yes);
        f.add(no);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            };

        });

        f.setVisible(true);

    }// main
}
