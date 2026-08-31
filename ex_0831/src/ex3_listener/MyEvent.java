package ex3_listener;

import java.awt.event.WindowListener;

public class MyEvent implements WindowListener{
    
    @Override
    public void windowOpened(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowIconified(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowDeiconified(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowActivated(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowDeactivated(java.awt.event.WindowEvent e) {

    }

}
