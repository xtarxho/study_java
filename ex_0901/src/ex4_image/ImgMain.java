package ex4_image;

import java.awt.*;
import java.awt.event.WindowAdapter;
import javax.swing.*;

public class ImgMain {
    public static void main(String[] args) {
        
        Frame f = new Frame();
        f.setLayout(null);//자동배치 끄기

        f.setBounds(500,100,700,1300);

        //이미지 참조(배경용)
        ImageIcon img = new ImageIcon("src/images/배경화면.jpg");
        JLabel jl = new JLabel(img);
        jl.setBounds(10,10,700,1300);

        //이미지(버튼)
        ImageIcon img2 = new ImageIcon("src/images/cp.png");
        JButton imgBtn = new JButton(img2);
        imgBtn.setBounds(50,50,175,157);

        //버튼 외곽선 없애기
        imgBtn.setBorderPainted(false);

        //버튼 배경 없애기
        imgBtn.setContentAreaFilled(false);

        //이미지 갱신할 때 호출하는 기능
        f.repaint();

        
        f.add(imgBtn);
        f.add(jl);

        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            };
        });

    }//main
}
