package sutda;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;

import javax.imageio.ImageIO;

/** 혼자 하기 화면입니다. */
public class GameFrame extends Frame implements Game.GameEventListener, ActionListener {
    private static final int W = 1100;
    private static final int H = 820;

    private final Game game;
    private final String playerName;
    private final List<SutdaCardView> firstCards = new ArrayList<SutdaCardView>();
    private final List<SutdaCardView> secondCards = new ArrayList<SutdaCardView>();
    private final List<Player> cardOwners = new ArrayList<Player>();

    private Panel root;
    private Panel table;

    private Label potLabel;
    private Label statusLabel;
    private Label resultLabel;
    private Label moneyLabel;
    private Label centerPotLabel;

    private Button callButton;
    private Button dieButton;
    private Button doubleButton;
    private Button allInButton;
    private Button startButton;
    private Button nextRoundButton;
    private Button chargeButton; // 충전 버튼

    private TextArea logTextArea;

    public GameFrame(String playerName) {
        super("Group 3 섯다");
        this.playerName = playerName;
        game = new Game(this);

        setSize(W, H);
        setResizable(false);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - W) / 2, (screen.height - H) / 2);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        // 화면을 만드는 코드를 생성자 안에 모았습니다.
        root = new Panel(new BorderLayout(8, 8));
        root.setBackground(new Color(18, 92, 55));
        add(root, BorderLayout.CENTER);

        Panel header = new Panel(new BorderLayout());
        header.setBackground(new Color(18, 92, 55));

        // 좌측 상단 패널 (팟 금액 + 충전 버튼)
        Panel topLeftPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        topLeftPanel.setBackground(new Color(18, 92, 55));

        potLabel = new Label("팟: 0원", Label.LEFT);
        potLabel.setForeground(Color.WHITE);
        potLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 19));

        chargeButton = new Button("충전");
        chargeButton.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
        chargeButton.addActionListener(this);

        topLeftPanel.add(potLabel);
        topLeftPanel.add(chargeButton);

        moneyLabel = new Label("내 칩: 0원", Label.RIGHT);
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 17));

        header.add(topLeftPanel, BorderLayout.WEST);
        header.add(moneyLabel, BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        Panel logPanel = new Panel(new BorderLayout(0, 5));
        logPanel.setBackground(new Color(18, 92, 55));
        logPanel.setPreferredSize(new Dimension(230, 0));

        Label logTitle = new Label("게임 진행 기록", Label.CENTER);
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

        statusLabel = new Label("", Label.CENTER);
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
        startButton = new Button("새 게임");

        Button[] buttons = { callButton, dieButton, doubleButton, allInButton, nextRoundButton, startButton };
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setFont(new Font("Malgun Gothic", Font.BOLD, 14));
            buttons[i].addActionListener(this);
            controls.add(buttons[i]);
        }

        nextRoundButton.setEnabled(false);
        bottom.add(statusLabel, BorderLayout.NORTH);
        bottom.add(resultLabel, BorderLayout.CENTER);
        bottom.add(controls, BorderLayout.SOUTH);
        root.add(bottom, BorderLayout.SOUTH);

        logTextArea.append("로그인 계정: " + playerName + "\n");
        logTextArea.append("저장된 전적: " + SaveData.getStats(playerName) + "\n");
        logTextArea.append("-------------------------\n");

        game.initGame(playerName);
    }

    // 모든 버튼 동작을 이 메서드 하나에서 처리합니다.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == callButton) {
            game.humanAction(1);
        } else if (source == dieButton) {
            game.humanAction(2);
        } else if (source == doubleButton) {
            game.humanAction(3);
        } else if (source == allInButton) {
            game.humanAction(4);
        } else if (source == nextRoundButton) {
            game.nextRound();
            refreshTableAndCards();
        } else if (source == startButton) {
            game.initGame(playerName);
            refreshTableAndCards();
        } else if (source == chargeButton) {
            // 충전 버튼 클릭 시 처리
            handleChargeProcess();
        }
    }

    /** 충전 버튼 클릭 시 농협 계좌 안내 및 Y/N 확인 후 도박신고 안내를 띄웁니다. */
    private void handleChargeProcess() {
        // 1. 농협 계좌 정보 팝업
        JOptionPane.showMessageDialog(this, "농협 000000", "입금 계좌 안내", JOptionPane.INFORMATION_MESSAGE);

        // 2. Y/N 확인 창 (예/아니오)
        int choice = JOptionPane.showConfirmDialog(
                this,
                "정말로 충전하시겠습니까?",
                "도박 경고 확인",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        // 사용자가 예(YES) 또는 아니오(NO)를 누른 경우 모두 도박신고 1336(1366) 안내로 연계
        if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(
                    this,
                    "도박중독 및 관련 신고는 도박문제 헬프라인 1336 (또는 1366)으로 문의하세요.",
                    "도박신고 안내",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    @Override
    public void onStateChanged() {
        List<Player> players = game.players;

        if (!players.isEmpty()) {
            moneyLabel.setText("내 칩: " + String.format("%,d원", players.get(0).money));
        }

        potLabel.setText("팟: " + String.format("%,d원", game.accumulatedPot));

        if (centerPotLabel != null) {
            centerPotLabel.setText("팟 " + String.format("%,d원", game.accumulatedPot));
        }

        updatePlayerCardsVisibility();
        setButtonsEnabled(game.waitingForAction);
    }

    @Override
    public void onStatusMessage(String message) {
        statusLabel.setText(message);
        logTextArea.append(message + "\n");
    }

    @Override
    public void onRoundFinished(String resultSummary, String resultMessage) {
        resultLabel.setText(resultMessage);
        statusLabel.setText(resultSummary);
        logTextArea.append("--- [라운드 종료] " + resultSummary + " ---\n");

        if (!resultSummary.equals("게임 종료") && !game.players.isEmpty()) {
            SaveData.saveRecord(playerName, resultSummary, resultMessage, game.players.get(0).money);
            logTextArea.append("현재 전적: " + SaveData.getStats(playerName) + "\n");
        }

        // 라운드 종료 시, 다이(folded 또는 out)하지 않은 생존 플레이어만 패를 공개하고 다이한 사람은 엄격히 숨김 유지
        for (int i = 0; i < firstCards.size() && i < cardOwners.size(); i++) {
            Player owner = cardOwners.get(i);
            if (!owner.out && !owner.folded) {
                firstCards.get(i).reveal();
                secondCards.get(i).reveal();
            } else {
                firstCards.get(i).hideCard();
                secondCards.get(i).hideCard();
            }
        }

        setButtonsEnabled(false);

        int activePlayers = 0;
        for (int i = 0; i < game.players.size(); i++) {
            if (!game.players.get(i).out) {
                activePlayers++;
            }
        }
        nextRoundButton.setEnabled(activePlayers > 1);
    }

    @Override
    public void onMidRoundCardReveal() {
        for (int i = 0; i < firstCards.size() && i < cardOwners.size(); i++) {
            Player owner = cardOwners.get(i);
            if (!owner.human && !owner.out && !owner.folded) {
                firstCards.get(i).reveal();
            }
        }
        // 중간에 카드가 열리더라도 다이했거나 기권한 사람이 있다면 즉시 덮어줍니다.
        updatePlayerCardsVisibility();
    }

    /** 플레이어가 다이(out 또는 folded)한 경우, 이미 한 장이 오픈되었더라도 패를 강제로 뒷면으로 숨깁니다. */
    private void updatePlayerCardsVisibility() {
        for (int i = 0; i < cardOwners.size() && i < firstCards.size(); i++) {
            Player owner = cardOwners.get(i);
            if (owner.out || owner.folded) {
                firstCards.get(i).hideCard();
                secondCards.get(i).hideCard();
            }
        }
    }

    /** 새 라운드가 시작되면 중앙 테이블을 다시 그립니다. */
    public void refreshTableAndCards() {
        if (table != null) {
            root.remove(table);
        }

        firstCards.clear();
        secondCards.clear();
        cardOwners.clear();

        table = new Panel(new BorderLayout(10, 10));
        table.setBackground(new Color(21, 111, 67));

        List<Player> players = game.players;
        if (players.size() >= 4) {
            table.add(makePlayerPanel(players.get(1), true), BorderLayout.NORTH);
            table.add(makePlayerPanel(players.get(2), true), BorderLayout.WEST);
            table.add(makePlayerPanel(players.get(3), true), BorderLayout.EAST);
            table.add(makePlayerPanel(players.get(0), false), BorderLayout.SOUTH);

            Panel center = new Panel(new GridLayout(3, 1, 0, 10));
            center.setBackground(new Color(21, 111, 67));

            Label title = new Label("SEUTDA", Label.CENTER);
            title.setForeground(new Color(255, 224, 135));
            title.setFont(new Font("Malgun Gothic", Font.BOLD, 22));
            center.add(title);

            centerPotLabel = new Label("팟 " + String.format("%,d원", game.accumulatedPot), Label.CENTER);
            centerPotLabel.setForeground(new Color(255, 224, 135));
            centerPotLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 26));
            centerPotLabel.setBackground(new Color(15, 70, 42));
            center.add(centerPotLabel);

            Label gameInfo = new Label("AI 3명과 겨루는 4인 섯다", Label.CENTER);
            gameInfo.setForeground(Color.WHITE);
            gameInfo.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
            center.add(gameInfo);

            table.add(center, BorderLayout.CENTER);
        }

        root.add(table, BorderLayout.CENTER);
        resultLabel.setText("");
        validate();
        repaint();
    }

    /** 한 플레이어의 이름, 카드, 족보를 보여주는 패널입니다. */
    private Panel makePlayerPanel(final Player player, boolean compact) {
        Panel panel = new Panel(new BorderLayout(5, 5));
        panel.setBackground(new Color(21, 111, 67));

        final Label name = new Label(
                player.name + ((player.out || player.folded) ? " [다이]" : "") + " (" + String.format("%,d원", player.money) + ")",
                Label.CENTER);
        if (player.human) {
            name.setForeground(new Color(255, 235, 130));
        } else {
            name.setForeground((player.out || player.folded) ? Color.GRAY : Color.WHITE);
        }
        name.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
        panel.add(name, BorderLayout.NORTH);

        Panel cards = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        cards.setBackground(new Color(21, 111, 67));

        int width = 88;
        int height = 132;
        if (compact) {
            width = 78;
            height = 116;
        }

        final SutdaCardView card1 = new SutdaCardView(player.card1, width, height);
        final SutdaCardView card2 = new SutdaCardView(player.card2, width, height);
        cards.add(card1);
        cards.add(card2);
        panel.add(cards, BorderLayout.CENTER);

        firstCards.add(card1);
        secondCards.add(card2);
        cardOwners.add(player);

        final Label handLabel = new Label("", Label.CENTER);
        handLabel.setForeground(new Color(255, 224, 135));
        handLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
        panel.add(handLabel, BorderLayout.SOUTH);

        // 람다식 대신 익명 Runnable을 사용했습니다.
        Runnable updateHand = new Runnable() {
            @Override
            public void run() {
                if (player.out || player.folded) {
                    card1.hideCard();
                    card2.hideCard();
                    handLabel.setText("다이 (기권)");
                } else if (card1.revealed && card2.revealed) {
                    handLabel.setText("족보: " + Card.evaluate(player.card1, player.card2).rank.name);
                } else {
                    handLabel.setText("");
                }
            }
        };

        card1.onRevealed = updateHand;
        card2.onRevealed = updateHand;

        if (player.human && !player.out && !player.folded) {
            card1.reveal();
            card2.reveal();
        }

        return panel;
    }

    /** 현재 내 차례인지에 따라 베팅 버튼을 켜고 끕니다. */
    private void setButtonsEnabled(boolean enabled) {
        if (callButton == null) {
            return;
        }

        long money = 0;
        if (!game.players.isEmpty()) {
            money = game.players.get(0).money;
        }

        callButton.setEnabled(enabled && money > 0);
        dieButton.setEnabled(enabled);
        doubleButton.setEnabled(enabled && money >= 2000L);
        allInButton.setEnabled(enabled && money > 0);

        if (enabled && nextRoundButton != null) {
            nextRoundButton.setEnabled(false);
        }
    }

    /** 카드 이미지를 resources 폴더에서 읽습니다. */
    private static BufferedImage loadImage(String path) {
        try {
            InputStream input = GameFrame.class.getResourceAsStream(path);
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

    /** 카드 한 장을 그리는 작은 화면입니다. */
    private class SutdaCardView extends Canvas {
        private final Card card;
        private final int cardWidth;
        private final int cardHeight;
        private boolean revealed;
        private Runnable onRevealed;
        private BufferedImage image;

        SutdaCardView(Card card, int width, int height) {
            this.card = card;
            cardWidth = width;
            cardHeight = height;
            image = loadImage("/images/card_back.png");
            setSize(width, height);
            setPreferredSize(new Dimension(width, height));
            setBackground(new Color(35, 35, 35));
        }

        void reveal() {
            if (revealed) {
                return;
            }

            revealed = true;
            image = loadImage(String.format("/images/card_%02d_%d.png", card.month, card.number));
            repaint();

            if (onRevealed != null) {
                onRevealed.run();
            }
        }

        // 다이 시 한 장이 오픈되어 있었더라도 상태를 초기화하고 뒷면 카드로 바꿉니다.
        void hideCard() {
            revealed = false;
            image = loadImage("/images/card_back.png");
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(230, 210, 150));
            g.drawRect(0, 0, cardWidth - 1, cardHeight - 1);

            if (image == null) {
                g.setColor(Color.WHITE);
                if (revealed) {
                    g.drawString(card.toString(), 10, cardHeight / 2);
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