package ex8_frame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.*;
public class FrameMain {
    public static void main(String[] args) {
        
        //숙제 1
        Frame f = new Frame("메모장");

        f.setLayout(null);
        f.setBounds(500, 100, 400, 300);

        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                Frame exitFrame = new Frame("종료 확인");

                exitFrame.setLayout(null);
                exitFrame.setBounds(f.getX() + 50, f.getY() + 100, 300, 150);

                Label msg = new Label("종료할까요?");
                msg.setBounds(100, 30, 100, 30);

                Button yes = new Button("네");
                yes.setBounds(50, 80, 80, 30);

                Button no = new Button("아니오");
                no.setBounds(170, 80, 80, 30);

                yes.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });

                no.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exitFrame.dispose();
                    }
                });

                exitFrame.add(msg);
                exitFrame.add(yes);
                exitFrame.add(no);

                exitFrame.setVisible(true);
            }
        });

        f.setVisible(true);
    

    }//main
}
