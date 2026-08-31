package ex1_frame;

import java.awt.*;

public class Ex1_Frame {
    public static void main(String[] args) {
        
        Frame frame = new Frame();
        
        frame.setSize(400,300); //너비 지정 메서드
        frame.setLocation(500,100); //시작위치 지정 메서드
        frame.setBackground(Color.YELLOW); //배경색
        
        frame.setVisible(true); //실제로 눈으로 확인할 수 있게 도와주는 메서드

    }//main
}
