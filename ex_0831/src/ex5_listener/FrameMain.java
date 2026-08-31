package ex5_listener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameMain {
    public static void main(String[] args) {
        
        Frame f = new Frame();
        f.setBounds(500,200,400,300);
        
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            };
        });

        f.setVisible(true);

    }//main
}
