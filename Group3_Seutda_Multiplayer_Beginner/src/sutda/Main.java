package sutda;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** 프로그램 실행과 로그인 화면을 담당합니다. */
public class Main {
    public static void main(String[] args) {
        final int LOGIN_W = 1280;
        final int LOGIN_H = 720;

        final Frame loginFrame = new Frame("Group 3 섯다 - Login");
        loginFrame.setResizable(false);

        LoginImage loginPanel = new LoginImage(LOGIN_W, LOGIN_H);

        final TextField idField = new TextField();
        idField.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
        idField.setBounds(487, 290, 307, 40);

        final TextField passwordField = new TextField();
        passwordField.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
        passwordField.setEchoChar('*');
        passwordField.setBounds(487, 365, 307, 40);

        Button loginButton = new Button("Login");
        loginButton.setFont(new Font("Serif", Font.BOLD, 22));
        loginButton.setBackground(new Color(91, 196, 239));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(487, 452, 307, 56);

        loginPanel.add(idField);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginFrame.add(loginPanel);

        // 람다식 대신 초보자가 보기 쉬운 ActionListener 형태를 사용했습니다.
        ActionListener login = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String password = passwordField.getText();

                if (id.isEmpty()) {
                    Toolkit.getDefaultToolkit().beep();
                    idField.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    Toolkit.getDefaultToolkit().beep();
                    passwordField.requestFocus();
                    return;
                }

                int loginResult = SaveData.login(id, password);

                if (loginResult == 0) {
                    Toolkit.getDefaultToolkit().beep();
                    loginFrame.setTitle("Group 3 섯다 - 비밀번호가 틀렸습니다");
                    passwordField.setText("");
                    passwordField.requestFocus();
                    return;
                }

                if (loginResult == -1) {
                    Toolkit.getDefaultToolkit().beep();
                    loginFrame.setTitle("Group 3 섯다 - 저장 파일을 확인할 수 없습니다");
                    return;
                }

                loginFrame.dispose();
                GameModeFrame modeFrame = new GameModeFrame(id);
                modeFrame.setVisible(true);
            }
        };

        loginButton.addActionListener(login);
        passwordField.addActionListener(login);

        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loginFrame.dispose();
                System.exit(0);
            }
        });

        loginFrame.pack();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        loginFrame.setLocation(
                (screen.width - loginFrame.getWidth()) / 2,
                (screen.height - loginFrame.getHeight()) / 2);
        loginFrame.setVisible(true);
        idField.requestFocus();
    }
}
