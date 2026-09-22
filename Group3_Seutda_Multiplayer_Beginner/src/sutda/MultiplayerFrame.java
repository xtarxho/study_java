package sutda;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/** 서버에서 받은 상태를 화면에 보여주는 멀티플레이 게임 창입니다. */
public class MultiplayerFrame extends Frame implements SeutdaClient.Listener, ActionListener {
    private static final int W = 1120;
    private static final int H = 840;

    private final String loginId;
    private final String serverIp;
    private final SeutdaServer localServer;
    private final SeutdaClient client;

    private int myId = -1;
    private boolean host;
    private boolean gameStarted;
    private SeutdaClient.GameState currentState;

    private Panel root;
    private Panel table;
    private Label roomLabel;
    private Label potLabel;
    private Label moneyLabel;
    private Label centerPotLabel;
    private Label statusLabel;
    private Label resultLabel;
    private TextArea logTextArea;

    private Button callButton;
    private Button dieButton;
    private Button doubleButton;
    private Button allInButton;
    private Button startButton;
    private Button nextRoundButton;

    public MultiplayerFrame(String loginId, String serverIp, SeutdaServer localServer) {
        super("Group 3 섯다 - Multiplayer");
        this.loginId = loginId;
        this.serverIp = serverIp;
        this.localServer = localServer;
        client = new SeutdaClient(this);

        setSize(W, H);
        setResizable(false);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - W) / 2, (screen.height - H) / 2);

        // 화면 생성 코드를 생성자에 모아서 작은 보조 메서드를 줄였습니다.
        root = new Panel(new BorderLayout(8, 8));
        root.setBackground(new Color(18, 92, 55));
        add(root, BorderLayout.CENTER);

        Panel header = new Panel(new BorderLayout());
        header.setBackground(new Color(18, 92, 55));

        roomLabel = new Label("서버 연결 중...", Label.LEFT);
        roomLabel.setForeground(new Color(255, 235, 150));
        roomLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 14));

        potLabel = new Label("팟: 0원", Label.CENTER);
        potLabel.setForeground(Color.WHITE);
        potLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 19));

        moneyLabel = new Label("내 칩: -", Label.RIGHT);
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 17));

        header.add(roomLabel, BorderLayout.WEST);
        header.add(potLabel, BorderLayout.CENTER);
        header.add(moneyLabel, BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        Panel logPanel = new Panel(new BorderLayout(0, 5));
        logPanel.setBackground(new Color(18, 92, 55));
        logPanel.setPreferredSize(new Dimension(255, 0));

        Label logTitle = new Label("멀티플레이 진행 기록", Label.CENTER);
        logTitle.setForeground(new Color(255, 224, 135));
        logTitle.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        logPanel.add(logTitle, BorderLayout.NORTH);

        logTextArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        logTextArea.setEditable(false);
        logTextArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
        logTextArea.setBackground(new Color(12, 65, 38));
        logTextArea.setForeground(Color.WHITE);
        logPanel.add(logTextArea, BorderLayout.CENTER);
        root.add(logPanel, BorderLayout.WEST);

        Panel bottom = new Panel(new BorderLayout(8, 5));
        bottom.setBackground(new Color(18, 92, 55));

        statusLabel = new Label("서버에 연결하고 있습니다.", Label.CENTER);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 14));

        resultLabel = new Label("", Label.CENTER);
        resultLabel.setForeground(Color.YELLOW);
        resultLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 17));

        Panel controls = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 4));
        controls.setBackground(new Color(18, 92, 55));

        callButton = new Button("콜");
        dieButton = new Button("다이");
        doubleButton = new Button("더블");
        allInButton = new Button("올인");
        nextRoundButton = new Button("다음 라운드");
        startButton = new Button("게임 시작");

        Button[] buttons = { callButton, dieButton, doubleButton, allInButton, nextRoundButton, startButton };
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setFont(new Font("Malgun Gothic", Font.BOLD, 14));
            buttons[i].addActionListener(this);
            controls.add(buttons[i]);
        }

        bottom.add(statusLabel, BorderLayout.NORTH);
        bottom.add(resultLabel, BorderLayout.CENTER);
        bottom.add(controls, BorderLayout.SOUTH);
        root.add(bottom, BorderLayout.SOUTH);

        setActionButtons(false);
        nextRoundButton.setEnabled(false);
        startButton.setEnabled(false);
        showWaitingPanel("서버 연결 중...");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.close();
                if (MultiplayerFrame.this.localServer != null) {
                    MultiplayerFrame.this.localServer.stop();
                }
                dispose();
                System.exit(0);
            }
        });

        connectToServer();
    }

    // 멀티플레이 버튼도 람다식 대신 이 메서드 하나에서 처리합니다.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == callButton) {
            client.sendAction(1);
        } else if (source == dieButton) {
            client.sendAction(2);
        } else if (source == doubleButton) {
            client.sendAction(3);
        } else if (source == allInButton) {
            client.sendAction(4);
        } else if (source == nextRoundButton) {
            client.nextRound();
        } else if (source == startButton) {
            client.startGame();
        }
    }

    /** 서버 접속은 화면이 멈추지 않도록 별도 Thread에서 실행합니다. */
    private void connectToServer() {
        Thread connectThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.connect(serverIp, SeutdaServer.DEFAULT_PORT, loginId);

                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (localServer != null) {
                                String ip = SeutdaServer.getLocalIpAddress();
                                roomLabel.setText("호스트 IP: " + ip + " : " + SeutdaServer.DEFAULT_PORT);
                                logTextArea.append("다른 사람에게 호스트 IP " + ip + " 를 알려주세요.\n");
                            } else {
                                roomLabel.setText("접속 서버: " + serverIp + " : " + SeutdaServer.DEFAULT_PORT);
                            }

                            logTextArea.append("로그인 계정: " + loginId + "\n");
                            logTextArea.append("저장된 전적: " + SaveData.getStats(loginId) + "\n");
                            logTextArea.append("-------------------------\n");
                        }
                    });
                } catch (final Exception ex) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            statusLabel.setText("서버 연결 실패: " + serverIp + " : " + SeutdaServer.DEFAULT_PORT);
                            resultLabel.setText("호스트 IP와 같은 네트워크인지 확인하세요.");
                            logTextArea.append("연결 실패: " + ex.getMessage() + "\n");
                        }
                    });
                }
            }
        }, "sutda-connect");

        connectThread.setDaemon(true);
        connectThread.start();
    }

    @Override
    public void onWelcome(final int id, final boolean hostPlayer) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (id > 0) {
                    myId = id;
                }

                host = hostPlayer;
                if (host) {
                    statusLabel.setText("내가 호스트입니다. 2명 이상 모이면 게임 시작을 누르세요.");
                } else {
                    statusLabel.setText("호스트가 게임을 시작할 때까지 기다리세요.");
                }

                if (!gameStarted) {
                    startButton.setVisible(host);
                }
            }
        });
    }

    @Override
    public void onLobby(final List<SeutdaClient.LobbyPlayer> players, final int maxPlayers) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameStarted = false;
                resultLabel.setText("");
                startButton.setVisible(host);
                startButton.setEnabled(host && players.size() >= 2);
                nextRoundButton.setEnabled(false);
                setActionButtons(false);
                showLobby(players, maxPlayers);
            }
        });
    }

    @Override
    public void onState(final SeutdaClient.GameState state) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameStarted = true;
                currentState = state;
                startButton.setVisible(false);
                potLabel.setText("팟: " + String.format("%,d원", state.pot));

                SeutdaClient.PlayerState me = null;
                for (int i = 0; i < state.players.size(); i++) {
                    if (state.players.get(i).id == myId) {
                        me = state.players.get(i);
                        break;
                    }
                }

                if (me != null) {
                    moneyLabel.setText("내 칩: " + String.format("%,d원", me.money));
                }

                String status = state.round + "라운드 / " + state.phase + "차 베팅";
                if (state.currentTurnId == myId) {
                    status += " - 내 차례입니다.";
                }
                statusLabel.setText(status);

                renderTable(state);

                boolean myTurn = false;
                if (!state.roundFinished && state.currentTurnId == myId && me != null && !me.folded && !me.out) {
                    myTurn = true;
                }

                setActionButtons(myTurn);

                if (me != null) {
                    callButton.setEnabled(myTurn && me.money > 0);
                    doubleButton.setEnabled(myTurn && me.money > 0);
                    allInButton.setEnabled(myTurn && me.money > 0);
                }

                nextRoundButton.setEnabled(state.roundFinished && !state.gameOver);
            }
        });
    }

    @Override
    public void onLog(final String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                logTextArea.append(message + "\n");
            }
        });
    }

    @Override
    public void onResult(final String summary, final String result) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                statusLabel.setText(summary);
                resultLabel.setText(result);
                logTextArea.append("--- [라운드 종료] " + summary + " ---\n");
                logTextArea.append(result + "\n");

                SeutdaClient.PlayerState me = null;
                if (currentState != null) {
                    for (int i = 0; i < currentState.players.size(); i++) {
                        if (currentState.players.get(i).id == myId) {
                            me = currentState.players.get(i);
                            break;
                        }
                    }
                }

                if (me != null && !summary.equals("게임 종료")) {
                    SaveData.saveRecord(loginId, summary, result, me.money);
                    logTextArea.append("현재 전적: " + SaveData.getStats(loginId) + "\n");
                }
            }
        });
    }

    @Override
    public void onError(final String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Toolkit.getDefaultToolkit().beep();
                resultLabel.setText(message);
                logTextArea.append("[오류] " + message + "\n");
            }
        });
    }

    @Override
    public void onDisconnected() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setActionButtons(false);
                startButton.setEnabled(false);
                nextRoundButton.setEnabled(false);
                statusLabel.setText("서버와의 연결이 종료되었습니다.");
                logTextArea.append("서버 연결이 종료되었습니다.\n");
            }
        });
    }

    /** 대기실에 접속한 사람 목록을 보여줍니다. */
    private void showLobby(List<SeutdaClient.LobbyPlayer> players, int maxPlayers) {
        if (table != null) {
            root.remove(table);
        }

        table = new Panel(new BorderLayout(10, 10));
        table.setBackground(new Color(21, 111, 67));

        Label title = new Label("멀티플레이 대기실  " + players.size() + " / " + maxPlayers, Label.CENTER);
        title.setForeground(new Color(255, 224, 135));
        title.setFont(new Font("Malgun Gothic", Font.BOLD, 25));
        table.add(title, BorderLayout.NORTH);

        Panel names = new Panel(new GridLayout(maxPlayers, 1, 5, 5));
        names.setBackground(new Color(21, 111, 67));

        for (int i = 0; i < maxPlayers; i++) {
            String text;

            if (i < players.size()) {
                SeutdaClient.LobbyPlayer p = players.get(i);
                text = (i + 1) + "번 자리 : " + p.name;
                if (p.host) {
                    text += "  [HOST]";
                }
            } else {
                text = (i + 1) + "번 자리 : 대기 중";
            }

            Label playerLabel = new Label(text, Label.CENTER);
            playerLabel.setForeground(Color.WHITE);
            playerLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
            names.add(playerLabel);
        }

        table.add(names, BorderLayout.CENTER);

        Label guide = new Label("같은 공유기/Wi-Fi의 최대 4명이 함께 플레이할 수 있습니다.", Label.CENTER);
        guide.setForeground(Color.WHITE);
        guide.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        table.add(guide, BorderLayout.SOUTH);

        root.add(table, BorderLayout.CENTER);
        validate();
        repaint();
    }

    /** 연결 중일 때 간단한 대기 문구를 보여줍니다. */
    private void showWaitingPanel(String text) {
        if (table != null) {
            root.remove(table);
        }

        table = new Panel(new BorderLayout());
        table.setBackground(new Color(21, 111, 67));

        Label waiting = new Label(text, Label.CENTER);
        waiting.setForeground(Color.WHITE);
        waiting.setFont(new Font("Malgun Gothic", Font.BOLD, 22));
        table.add(waiting, BorderLayout.CENTER);

        root.add(table, BorderLayout.CENTER);
        validate();
        repaint();
    }

    /** 서버가 보낸 현재 게임 상태를 테이블 화면에 표시합니다. */
    private void renderTable(SeutdaClient.GameState state) {
        if (table != null) {
            root.remove(table);
        }

        table = new Panel(new BorderLayout(10, 10));
        table.setBackground(new Color(21, 111, 67));

        List<SeutdaClient.PlayerState> others = new ArrayList<SeutdaClient.PlayerState>();
        SeutdaClient.PlayerState me = null;

        for (int i = 0; i < state.players.size(); i++) {
            SeutdaClient.PlayerState p = state.players.get(i);
            if (p.id == myId) {
                me = p;
            } else {
                others.add(p);
            }
        }

        if (others.size() > 0) {
            table.add(makePlayerPanel(others.get(0), true), BorderLayout.NORTH);
        }
        if (others.size() > 1) {
            table.add(makePlayerPanel(others.get(1), true), BorderLayout.WEST);
        }
        if (others.size() > 2) {
            table.add(makePlayerPanel(others.get(2), true), BorderLayout.EAST);
        }
        if (me != null) {
            table.add(makePlayerPanel(me, false), BorderLayout.SOUTH);
        }

        Panel center = new Panel(new GridLayout(4, 1, 0, 8));
        center.setBackground(new Color(21, 111, 67));

        Label title = new Label("SEUTDA ONLINE", Label.CENTER);
        title.setForeground(new Color(255, 224, 135));
        title.setFont(new Font("Malgun Gothic", Font.BOLD, 22));
        center.add(title);

        centerPotLabel = new Label("팟 " + String.format("%,d원", state.pot), Label.CENTER);
        centerPotLabel.setForeground(new Color(255, 224, 135));
        centerPotLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 25));
        centerPotLabel.setBackground(new Color(15, 70, 42));
        center.add(centerPotLabel);

        Label betInfo = new Label(state.phase + "차 베팅 / 기준 " + String.format("%,d원", state.targetBet), Label.CENTER);
        betInfo.setForeground(Color.WHITE);
        betInfo.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
        center.add(betInfo);

        String turnText = "라운드 결과 확인 중";
        if (!state.roundFinished) {
            turnText = "차례 계산 중";
            for (int i = 0; i < state.players.size(); i++) {
                if (state.players.get(i).id == state.currentTurnId) {
                    turnText = "현재 차례: " + state.players.get(i).name;
                    break;
                }
            }
        }

        Label turnLabel = new Label(turnText, Label.CENTER);
        if (state.currentTurnId == myId) {
            turnLabel.setForeground(Color.YELLOW);
        } else {
            turnLabel.setForeground(Color.WHITE);
        }
        turnLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        center.add(turnLabel);

        table.add(center, BorderLayout.CENTER);
        root.add(table, BorderLayout.CENTER);
        validate();
        repaint();
    }

    /** 한 명의 이름, 돈, 카드, 베팅 상태를 표시합니다. */
    private Panel makePlayerPanel(SeutdaClient.PlayerState player, boolean compact) {
        Panel panel = new Panel(new BorderLayout(5, 5));
        panel.setBackground(new Color(21, 111, 67));

        String stateText = "";
        if (player.out) {
            stateText = " [탈락]";
        } else if (player.folded) {
            stateText = " [다이]";
        } else if (player.money == 0) {
            stateText = " [올인]";
        }

        Label name = new Label(player.name + stateText + "  " + String.format("%,d원", player.money), Label.CENTER);
        if (player.id == myId) {
            name.setForeground(new Color(255, 235, 130));
        } else {
            name.setForeground(Color.WHITE);
        }
        name.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        panel.add(name, BorderLayout.NORTH);

        Panel cards = new Panel(new FlowLayout(FlowLayout.CENTER, 8, 2));
        cards.setBackground(new Color(21, 111, 67));

        int width = 88;
        int height = 132;
        if (compact) {
            width = 76;
            height = 112;
        }

        cards.add(new NetworkCardView(player.card1, player.showCard1, width, height));
        cards.add(new NetworkCardView(player.card2, player.showCard2, width, height));
        panel.add(cards, BorderLayout.CENTER);

        String hand = "베팅: " + String.format("%,d원", player.currentBet);
        if (player.handName != null && !player.handName.isEmpty()) {
            hand += " / 족보: " + player.handName;
        }

        Label handLabel = new Label(hand, Label.CENTER);
        handLabel.setForeground(new Color(255, 224, 135));
        handLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
        panel.add(handLabel, BorderLayout.SOUTH);

        return panel;
    }

    /** 내 차례일 때만 베팅 버튼을 사용할 수 있게 합니다. */
    private void setActionButtons(boolean enabled) {
        if (callButton == null) {
            return;
        }

        callButton.setEnabled(enabled);
        dieButton.setEnabled(enabled);
        doubleButton.setEnabled(enabled);
        allInButton.setEnabled(enabled);
    }

    /** 카드 이미지를 resources 폴더에서 읽습니다. */
    private static BufferedImage loadImage(String path) {
        try {
            InputStream input = MultiplayerFrame.class.getResourceAsStream(path);
            if (input != null) {
                BufferedImage image = ImageIO.read(input);
                input.close();
                return image;
            }
        } catch (Exception ignored) {
        }

        try {
            File file = new File("resources" + path);
            if (file.exists()) {
                return ImageIO.read(file);
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    /** 서버가 공개해도 된다고 한 카드만 앞면으로 보여줍니다. */
    private class NetworkCardView extends Canvas {
        private final int cardNumber;
        private final boolean revealed;
        private final int cardWidth;
        private final int cardHeight;
        private final BufferedImage image;

        NetworkCardView(int cardNumber, boolean revealed, int width, int height) {
            this.cardNumber = cardNumber;
            this.revealed = revealed && cardNumber > 0;
            cardWidth = width;
            cardHeight = height;

            if (this.revealed) {
                Card card = new Card(cardNumber);
                image = loadImage(String.format("/images/card_%02d_%d.png", card.month, card.number));
            } else {
                image = loadImage("/images/card_back.png");
            }

            setSize(width, height);
            setPreferredSize(new Dimension(width, height));
            setBackground(new Color(35, 35, 35));
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(230, 210, 150));
            g.drawRect(0, 0, cardWidth - 1, cardHeight - 1);

            if (image == null) {
                g.setColor(Color.WHITE);
                if (revealed) {
                    g.drawString(String.valueOf(cardNumber), 10, cardHeight / 2);
                } else {
                    g.drawString("?", 10, cardHeight / 2);
                }
                return;
            }

            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            double scale = Math.min(
                    (double) (cardWidth - 4) / imageWidth,
                    (double) (cardHeight - 4) / imageHeight);

            int drawWidth = Math.max(1, (int) (imageWidth * scale));
            int drawHeight = Math.max(1, (int) (imageHeight * scale));
            int x = (cardWidth - drawWidth) / 2;
            int y = (cardHeight - drawHeight) / 2;

            Image scaled = image.getScaledInstance(drawWidth, drawHeight, Image.SCALE_SMOOTH);
            g.drawImage(scaled, x, y, this);
        }
    }
}
