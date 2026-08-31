package ex4_listener;

import java.awt.event.WindowEvent;

public class MyClosingListener extends ListenerClass {
    @Override
    public void windowClosing(WindowEvent e){
        System.out.println("종료할거야");
        System.exit(0);
    }
    
    @Override
    public void windowIconified(WindowEvent e){
        // 창이 최소화될 때 호출
        System.out.println("최소화가 되었음");
    }

    
}
