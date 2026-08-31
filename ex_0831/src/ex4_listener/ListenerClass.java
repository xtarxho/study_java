package ex4_listener;

import java.awt.event.WindowListener;

public class ListenerClass implements WindowListener{
    @Override
    public void windowOpened(java.awt.event.WindowEvent e) {
        // 창이 열릴 때 호출됩니다.
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        // 창이 닫히려고 할 때 호출됩니다.
        System.exit(0);
    }

    @Override
    public void windowClosed(java.awt.event.WindowEvent e) {
        // 창이 닫힌 후에 호출됩니다.
    }

    @Override
    public void windowIconified(java.awt.event.WindowEvent e) {
        // 창이 최소화될 때 호출됩니다.
    }

    @Override
    public void windowDeiconified(java.awt.event.WindowEvent e) {
        // 창이 다시 활성화(최소화 해제)될 때 호출됩니다.
    }

    @Override
    public void windowActivated(java.awt.event.WindowEvent e) {
        // 창이 활성 상태가 될 때 호출됩니다.
    }

    @Override
    public void windowDeactivated(java.awt.event.WindowEvent e) {
        // 창이 비활성 상태가 될 때 호출됩니다.
    }
}
