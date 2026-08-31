package ex1_frame;

import java.awt.Color;

public class FrameMain {
    public static void main(String[] args) {
        
        MyFrame mf = new MyFrame();

        MyFrame mf2 = new MyFrame();
        mf2.setBackground(Color.BLUE);
        mf2.setTitle("나는 2번 프레임");

    }//main
}
