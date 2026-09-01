package ex9_frame;

import java.awt.*;
import java.awt.event.WindowAdapter;

public class FrameMain {
    public static void main(String[] args) {
        

        //클래스 나눠서 만들기
        //창닫기 이벤트 추가 -> 종료 확인 창 띄우기 -> 종료 확인 창에서 네 버튼 누르면 종료, 아니오 버튼 누르면 창 닫기
        Frame f = new Frame();
        f.setBounds(500,200,500,400);
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                
                new NewFrame(f);

                
                    
                
                    
                
                
            }
        });

    }//main
}
