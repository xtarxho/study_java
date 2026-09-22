package sutda;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** 로그인 뒤 혼자 하기 / 방 만들기 / 방 참가를 고르는 화면입니다. */
public class GameModeFrame extends Frame implements ActionListener {
    private final String playerName;
    private final Label messageLabel;
    private final TextField ipField;

    private final Button soloButton;
    private final Button hostButton;
    private final Button joinButton;

    public GameModeFrame(String playerName) {
        super("Group 3 섯다 - 게임 모드 선택");
        this.playerName = playerName;

        setSize(680, 390);
        setResizable(false);
        setBackground(new Color(18, 92, 55));
        setLayout(new BorderLayout(15, 15));

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);

        Label title = new Label("게임 모드를 선택하세요", Label.CENTER);
        title.setFont(new Font("Malgun Gothic", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        Panel center = new Panel(new GridLayout(3, 1, 8, 8));
        center.setBackground(new Color(18, 92, 55));

        // 버튼을 만드는 작은 메서드를 없애고 여기에서 바로 만들었습니다.
        soloButton = new Button("혼자 하기 (AI 3명)");
        soloButton.setFont(new Font("Malgun Gothic", Font.BOLD, 17));
        soloButton.setBackground(new Color(240, 240, 240));
        soloButton.addActionListener(this);
        center.add(soloButton);

        hostButton = new Button("방 만들기 (내 컴퓨터가 호스트)");
        hostButton.setFont(new Font("Malgun Gothic", Font.BOLD, 17));
        hostButton.setBackground(new Color(240, 240, 240));
        hostButton.addActionListener(this);
        center.add(hostButton);

        Panel joinPanel = new Panel(new BorderLayout(8, 4));
        joinPanel.setBackground(new Color(18, 92, 55));

        ipField = new TextField();
        ipField.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
        ipField.addActionListener(this);

        joinButton = new Button("방 참가");
        joinButton.setFont(new Font("Malgun Gothic", Font.BOLD, 17));
        joinButton.setBackground(new Color(240, 240, 240));
        joinButton.addActionListener(this);

        joinPanel.add(new Label("호스트 IP 입력", Label.CENTER), BorderLayout.WEST);
        joinPanel.add(ipField, BorderLayout.CENTER);
        joinPanel.add(joinButton, BorderLayout.EAST);
        center.add(joinPanel);

        add(center, BorderLayout.CENTER);

        Panel bottom = new Panel(new FlowLayout(FlowLayout.CENTER));
        bottom.setBackground(new Color(18, 92, 55));
        messageLabel = new Label("같은 Wi-Fi/공유기에 연결된 컴퓨터끼리 바로 플레이할 수 있습니다.", Label.CENTER);
        messageLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
        messageLabel.setForeground(new Color(255, 235, 150));
        bottom.add(messageLabel);
        add(bottom, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    // 람다식 대신 버튼을 누르면 이 메서드 하나에서 어떤 버튼인지 확인합니다.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == soloButton) {
            dispose();
            GameFrame frame = new GameFrame(playerName);
            frame.refreshTableAndCards();
            frame.setVisible(true);
            return;
        }

        if (source == hostButton) {
            try {
                SeutdaServer server = new SeutdaServer();
                server.start();
                dispose();
                MultiplayerFrame frame = new MultiplayerFrame(playerName, "127.0.0.1", server);
                frame.setVisible(true);
            } catch (Exception ex) {
                messageLabel.setText("방을 만들 수 없습니다. 포트 9999 사용 여부를 확인하세요.");
                Toolkit.getDefaultToolkit().beep();
            }
            return;
        }

        if (source == joinButton || source == ipField) {
            String ip = ipField.getText().trim();

            if (ip.isEmpty()) {
                messageLabel.setText("방을 만든 사람 화면에 표시된 호스트 IP를 입력하세요.");
                Toolkit.getDefaultToolkit().beep();
                ipField.requestFocus();
                return;
            }

            dispose();
            MultiplayerFrame frame = new MultiplayerFrame(playerName, ip, null);
            frame.setVisible(true);
        }
    }
}
