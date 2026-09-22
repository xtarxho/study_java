package sutda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 섯다 서버와 통신하는 클라이언트입니다.
 * 화면 클래스는 이 객체를 통해 START / ACTION / NEXT 명령만 보냅니다.
 */
public class SeutdaClient {
    public interface Listener {
        void onWelcome(int myId, boolean host);

        void onLobby(List<LobbyPlayer> players, int maxPlayers);

        void onState(GameState state);

        void onLog(String message);

        void onResult(String summary, String result);

        void onError(String message);

        void onDisconnected();
    }

    public static class LobbyPlayer {
        public int id;
        public String name;
        public boolean host;
    }

    public static class PlayerState {
        public int id;
        public String name;
        public long money;
        public long currentBet;
        public boolean folded;
        public boolean out;
        public boolean rematchTarget;
        public int card1;
        public int card2;
        public boolean showCard1;
        public boolean showCard2;
        public String handName;
    }

    public static class GameState {
        public int round;
        public int phase;
        public long pot;
        public long targetBet;
        public int currentTurnId;
        public boolean roundFinished;
        public boolean gameOver;
        public boolean revealFirstCards;
        public final List<PlayerState> players = new ArrayList<>();
    }

    private final Listener listener;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Thread readerThread;
    private volatile boolean connected;

    public SeutdaClient(Listener listener) {
        this.listener = listener;
    }

    /** 서버 IP에 연결하고 닉네임을 전송합니다. */
    public void connect(String host, int port, String playerName) throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), 3000);
        socket.setTcpNoDelay(true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        connected = true;
        out.println("JOIN\t" + encode(playerName));

        readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                readLoop();
            }
        }, "sutda-client-reader");
        readerThread.setDaemon(true);
        readerThread.start();
    }

    public void startGame() {
        send("START");
    }

    public void sendAction(int action) {
        send("ACTION\t" + action);
    }

    public void nextRound() {
        send("NEXT");
    }

    public synchronized void close() {
        connected = false;
        try {
            if (socket != null)
                socket.close();
        } catch (IOException ignored) {
        }
    }

    private synchronized void send(String message) {
        if (connected && out != null)
            out.println(message);
    }

    private void readLoop() {
        try {
            String line;
            while (connected && (line = in.readLine()) != null)
                parse(line);
        } catch (IOException ignored) {
        } finally {
            boolean notify = connected;
            close();
            if (notify && listener != null)
                listener.onDisconnected();
        }
    }

    private void parse(String line) {
        String[] value = line.split("\\t");
        if (value.length == 0)
            return;

        try {
            if ("WELCOME".equals(value[0]) && value.length >= 3) {
                listener.onWelcome(Integer.parseInt(value[1]), Boolean.parseBoolean(value[2]));
            } else if ("HOST".equals(value[0]) && value.length >= 2) {
                // 호스트가 나간 경우 새 호스트 여부만 다시 알려줍니다.
                listener.onWelcome(-1, Boolean.parseBoolean(value[1]));
            } else if ("LOBBY".equals(value[0]) && value.length >= 3) {
                List<LobbyPlayer> players = new ArrayList<>();
                int max = Integer.parseInt(value[2]);
                for (int i = 3; i < value.length; i++) {
                    String[] p = value[i].split(",", 3);
                    if (p.length < 3)
                        continue;
                    LobbyPlayer lp = new LobbyPlayer();
                    lp.id = Integer.parseInt(p[0]);
                    lp.name = decode(p[1]);
                    lp.host = Boolean.parseBoolean(p[2]);
                    players.add(lp);
                }
                listener.onLobby(players, max);
            } else if ("STATE".equals(value[0])) {
                listener.onState(parseState(value));
            } else if ("LOG".equals(value[0]) && value.length >= 2) {
                listener.onLog(decode(value[1]));
            } else if ("RESULT".equals(value[0]) && value.length >= 3) {
                listener.onResult(decode(value[1]), decode(value[2]));
            } else if ("ERROR".equals(value[0]) && value.length >= 2) {
                listener.onError(decode(value[1]));
            }
        } catch (Exception e) {
            if (listener != null)
                listener.onError("서버 메시지를 읽는 중 오류가 발생했습니다.");
        }
    }

    private GameState parseState(String[] value) {
        GameState state = new GameState();
        state.round = Integer.parseInt(value[1]);
        state.phase = Integer.parseInt(value[2]);
        state.pot = Long.parseLong(value[3]);
        state.targetBet = Long.parseLong(value[4]);
        state.currentTurnId = Integer.parseInt(value[5]);
        state.roundFinished = Boolean.parseBoolean(value[6]);
        state.gameOver = Boolean.parseBoolean(value[7]);
        state.revealFirstCards = Boolean.parseBoolean(value[8]);
        int count = Integer.parseInt(value[9]);

        for (int i = 0; i < count && 10 + i < value.length; i++) {
            String[] p = value[10 + i].split(",", 12);
            if (p.length < 12)
                continue;
            PlayerState ps = new PlayerState();
            ps.id = Integer.parseInt(p[0]);
            ps.name = decode(p[1]);
            ps.money = Long.parseLong(p[2]);
            ps.currentBet = Long.parseLong(p[3]);
            ps.folded = Boolean.parseBoolean(p[4]);
            ps.out = Boolean.parseBoolean(p[5]);
            ps.rematchTarget = Boolean.parseBoolean(p[6]);
            ps.card1 = Integer.parseInt(p[7]);
            ps.card2 = Integer.parseInt(p[8]);
            ps.showCard1 = Boolean.parseBoolean(p[9]);
            ps.showCard2 = Boolean.parseBoolean(p[10]);
            ps.handName = decode(p[11]);
            state.players.add(ps);
        }
        return state;
    }

    static String encode(String value) {
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    static String decode(String value) {
        if (value == null || value.isEmpty())
            return "";
        return new String(Base64.getUrlDecoder().decode(value), StandardCharsets.UTF_8);
    }
}
