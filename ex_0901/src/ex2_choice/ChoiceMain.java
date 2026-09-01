package ex2_choice;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;

public class ChoiceMain {
    public static void main(String[] args) {
        
        Frame f = new Frame();
        f.setBounds(500,100,500,300);
        f.setLayout(null); //자동배치 끄기

        Choice region = new Choice();
        region.add("지역선택");
        region.add("서울");
        region.add("경기");
        region.add("인천");
        region.add("광주");
        region.add("대구");
        region.add("대전");
        region.add("부산");
        region.setBounds(50,100,100,0);

        Label resLabel = new Label();
        resLabel.setBounds(170,200,100,30);

        //Choice객체의 변경을 감지하는 감지자를 추가
        region.addItemListener(new ChoiceListener(resLabel) ); //ChoiceListener 클래스

        f.add(resLabel);
        f.add(region);


        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            };
        });

    }//main
}
