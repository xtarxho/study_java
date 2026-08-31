package ex3_listener;

import java.awt.*;

public class FrameMain {
    public static void main(String[] args) {
        
        Frame f = new Frame();
        // f.setSize(300,300);
        // f.setLocation(500,200);

        //location,size한번에 사용하게 하는 메서드
        f.setBounds(500,200,300,300); 

        //우상단 x버튼 클릭 감지
        f.addWindowListener(new MyEvent());

        f.setVisible(true);

    }//main
}
