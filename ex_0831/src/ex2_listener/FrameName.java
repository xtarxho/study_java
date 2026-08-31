package ex2_listener;

import java.awt.*;
import java.awt.event.WindowListener;

public class FrameName {
    public static void main(String[] args) {
        
        Frame f = new Frame();
        f.setLocation(600,200);
        f.setSize(300,200);

        //생성된 프레임의 우상단 메뉴에 대한 클릭을 감지
        WindowListener listen = new WindowListener(){
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {}

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // 프로그램 종료
                System.out.println("종료를 시도함");
                System.exit(0);
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {}

            @Override
            public void windowIconified(java.awt.event.WindowEvent e) {}

            @Override
            public void windowDeiconified(java.awt.event.WindowEvent e) {}

            @Override
            public void windowActivated(java.awt.event.WindowEvent e) {}

            @Override
            public void windowDeactivated(java.awt.event.WindowEvent e) {}


        };

        f.addWindowListener(listen);

        f.setVisible(true);

    }//main 
}
