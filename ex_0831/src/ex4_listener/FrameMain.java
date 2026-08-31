package ex4_listener;

import java.awt.*;

public class FrameMain {
    public static void main(String[] args) {
        
        Frame f = new Frame();
        f.setBounds(500,100,400,300);

        f.addWindowListener(new MyClosingListener());

        f.setVisible(true);

    }//main
}
