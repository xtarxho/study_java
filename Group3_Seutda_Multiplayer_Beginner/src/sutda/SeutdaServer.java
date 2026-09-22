package sutda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 멀티플레이 섯다 서버입니다.
 *
 * 서버 한 곳에서 카드 분배, 돈, 팟, 차례, 승패를 계산하고
 * 클라이언트는 서버가 보내준 결과만 화면에 표시합니다.
 * 그래서 여러 컴퓨터에서 접속해도 같은 게임 상태를 보게 됩니다.
 */
public class SeutdaServer {
    public static final int DEFAULT_PORT = 9999;
    public static final int MAX_PLAYERS = 4;

    private final int port;
    private final List<ClientHandler> clients = new ArrayList<>();

    private ServerSocket serverSocket;
    private Thread acceptThread;
    private volatile boolean running;
    private volatile boolean gameStarted;
    private int nextClientId = 1;
    private GameSession game;

    public SeutdaServer() {
        this(DEFAULT_PORT);
    }

    public SeutdaServer(int port) {
        this.port = port;
    }

    /** 호스트가 누르면 서버를 실제로 엽니다. 포트 충돌은 여기서 바로 확인됩니다. */
    public synchronized void start() throws IOException {
        if (running)
            return;

        serverSocket = new ServerSocket(port);
        running = true;
        acceptThread = new Thread(new Runnable() {
            @Override
            public void run() {
                acceptLoop();
            }
        }, "sutda-server-accept");
        acceptThread.setDaemon(true);
        acceptThread.start();
        System.out.println("[서버] 섯다 서버 시작 - 포트 " + port);
    }

    /** 호스트 창이 닫히면 서버와 연결된 소켓을 정리합니다. */
    public synchronized void stop() {
        running = false;
        try {
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException ignored) {
        }

        List<ClientHandler> copy;
        synchronized (clients) {
            copy = new ArrayList<>(clients);
        }
        for (ClientHandler client : copy)
            client.close();
    }

    public boolean isRunning() {
        return running;
    }

    private void acceptLoop() {
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                socket.setTcpNoDelay(true);

                synchronized (clients) {
                    if (gameStarted || clients.size() >= MAX_PLAYERS) {
                        PrintWriter tempOut = new PrintWriter(
                                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                        tempOut.println("ERROR\t" + encode(gameStarted ? "이미 게임이 시작되었습니다." : "방이 가득 찼습니다."));
                        socket.close();
                        continue;
                    }
                }

                ClientHandler client = new ClientHandler(socket, nextClientId++);
                Thread thread = new Thread(client, "sutda-client-" + client.id);
                thread.setDaemon(true);
                thread.start();
            } catch (IOException e) {
                if (running)
                    System.out.println("[서버] 연결 대기 중 오류: " + e.getMessage());
            }
        }
    }

    private void joined(ClientHandler client, String requestedName) {
        synchronized (clients) {
            if (gameStarted || clients.size() >= MAX_PLAYERS) {
                client.send("ERROR\t" + encode("지금은 참가할 수 없습니다."));
                client.close();
                return;
            }

            client.name = uniqueName(cleanName(requestedName));
            client.host = false;
            clients.add(client);
            recomputeHostLocked();
        }

        client.send("WELCOME\t" + client.id + "\t" + client.host);
        sendHostFlags();
        broadcastLog(client.name + " 님이 방에 들어왔습니다.");
        broadcastLobby();
    }

    private void recomputeHostLocked() {
        ClientHandler first = null;
        for (ClientHandler c : clients) {
            if (first == null || c.id < first.id)
                first = c;
        }
        for (ClientHandler c : clients)
            c.host = c == first;
    }

    private void sendHostFlags() {
        synchronized (clients) {
            for (ClientHandler c : clients)
                c.send("HOST\t" + c.host);
        }
    }

    private String uniqueName(String requested) {
        String base = requested.isEmpty() ? "플레이어" : requested;
        String result = base;
        int number = 2;
        boolean duplicated;
        do {
            duplicated = false;
            for (ClientHandler c : clients) {
                if (c.name != null && c.name.equals(result)) {
                    duplicated = true;
                    result = base + number++;
                    break;
                }
            }
        } while (duplicated);
        return result;
    }

    private void handleCommand(ClientHandler client, String line) {
        if (line == null)
            return;

        String[] value = line.split("\\t", 2);
        String command = value[0];

        if ("START".equals(command)) {
            startGame(client);
        } else if ("ACTION".equals(command) && value.length == 2) {
            try {
                int action = Integer.parseInt(value[1]);
                GameSession current = game;
                if (current != null)
                    current.action(client, action);
            } catch (NumberFormatException ignored) {
            }
        } else if ("NEXT".equals(command)) {
            GameSession current = game;
            if (current != null)
                current.nextRound(client);
        }
    }

    private void startGame(ClientHandler requester) {
        List<ClientHandler> playerClients;
        synchronized (clients) {
            if (gameStarted)
                return;
            if (!requester.host) {
                requester.send("ERROR\t" + encode("호스트만 게임을 시작할 수 있습니다."));
                return;
            }
            if (clients.size() < 2) {
                requester.send("ERROR\t" + encode("멀티플레이는 최소 2명이 필요합니다."));
                return;
            }
            gameStarted = true;
            playerClients = new ArrayList<>(clients);
        }

        broadcastLog("게임을 시작합니다. 참가 인원: " + playerClients.size() + "명");
        game = new GameSession(playerClients);
        game.startFirstRound();
    }

    private void disconnected(ClientHandler client) {
        boolean wasInList;
        synchronized (clients) {
            wasInList = clients.remove(client);
            if (!wasInList)
                return;

            if (!gameStarted)
                recomputeHostLocked();
        }

        if (gameStarted && game != null) {
            game.disconnect(client);
        } else {
            broadcastLog((client.name == null ? "플레이어" : client.name) + " 님이 나갔습니다.");
            broadcastLobby();
            sendHostFlags();
        }
    }

    private void broadcastLobby() {
        List<ClientHandler> copy;
        synchronized (clients) {
            copy = new ArrayList<>(clients);
        }

        StringBuilder line = new StringBuilder("LOBBY\t").append(copy.size()).append("\t").append(MAX_PLAYERS);
        for (ClientHandler c : copy) {
            line.append("\t").append(c.id).append(",").append(encode(c.name)).append(",").append(c.host);
        }
        for (ClientHandler c : copy)
            c.send(line.toString());
    }

    private void broadcastLog(String message) {
        String line = "LOG\t" + encode(message);
        List<ClientHandler> copy;
        synchronized (clients) {
            copy = new ArrayList<>(clients);
        }
        for (ClientHandler c : copy)
            c.send(line);
        System.out.println("[게임] " + message);
    }

    private void broadcastResult(String summary, String result) {
        String line = "RESULT\t" + encode(summary) + "\t" + encode(result);
        List<ClientHandler> copy;
        synchronized (clients) {
            copy = new ArrayList<>(clients);
        }
        for (ClientHandler c : copy)
            c.send(line);
    }

    /** 서버가 계산한 현재 게임 상태를 각 사용자에게 맞게 전송합니다. */
    private void broadcastState(GameSession session) {
        List<ClientHandler> copy;
        synchronized (clients) {
            copy = new ArrayList<>(clients);
        }

        for (ClientHandler receiver : copy) {
            StringBuilder line = new StringBuilder("STATE")
                    .append("\t").append(session.round)
                    .append("\t").append(session.phase)
                    .append("\t").append(session.pot)
                    .append("\t").append(session.targetBet)
                    .append("\t").append(session.currentTurnId())
                    .append("\t").append(session.roundFinished)
                    .append("\t").append(session.gameOver)
                    .append("\t").append(session.revealFirstCards)
                    .append("\t").append(session.players.size());

            for (ServerPlayer p : session.players) {
                boolean own = p.client == receiver;
                // [수정] 기권승(foldedWin)인 경우 라운드가 끝나도 다른 사람 패를 공개하지 않음[cite: 33, 35]
                boolean showFirst = own || session.revealFirstCards || (session.roundFinished && !session.foldedWin);
                boolean showSecond = own || (session.roundFinished && !session.foldedWin);
                int card1 = p.card1 == null ? 0 : p.card1.number;
                int card2 = p.card2 == null ? 0 : p.card2.number;
                String hand = "";
                if (p.card1 != null && p.card2 != null && (own || session.roundFinished))
                    hand = Card.evaluate(p.card1, p.card2).rank.name;

                line.append("\t")
                        .append(p.client.id).append(",")
                        .append(encode(p.name)).append(",")
                        .append(p.money).append(",")
                        .append(p.currentBet).append(",")
                        .append(p.folded).append(",")
                        .append(p.out).append(",")
                        .append(p.rematchTarget).append(",")
                        .append(card1).append(",")
                        .append(card2).append(",")
                        .append(showFirst).append(",")
                        .append(showSecond).append(",")
                        .append(encode(hand));
            }
            receiver.send(line.toString());
        }
    }

    private static String cleanName(String name) {
        if (name == null)
            return "플레이어";
        String clean = name.replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
        if (clean.length() > 16)
            clean = clean.substring(0, 16);
        return clean;
    }

    static String encode(String value) {
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    /** 참가자에게 알려줄 수 있는 같은 공유기 안의 IPv4 주소를 찾습니다. */
    public static String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface network = interfaces.nextElement();
                if (!network.isUp() || network.isLoopback())
                    continue;
                Enumeration<InetAddress> addresses = network.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address instanceof Inet4Address && address.isSiteLocalAddress())
                        return address.getHostAddress();
                }
            }
        } catch (Exception ignored) {
        }
        return "127.0.0.1";
    }

    /**
     * 서버가 실제 섯다 규칙을 계산하는 부분입니다.
     * 네트워크에서는 이 객체 하나만 돈과 카드를 변경합니다.
     */
    private class GameSession {
        private static final long INITIAL_MONEY = 100_000_000L;
        private static final long BASE_ENTRY_FEE = 1_000L;
        private static final long BASE_BET_UNIT = 10_000L;
        private static final int TOTAL_PHASES = 4;

        private final List<ServerPlayer> players = new ArrayList<>();
        private final int initialPlayerCount;
        private final Set<Integer> pending = new HashSet<>();

        private long pot;
        private long targetBet;
        private long multiplier = 1;
        private int round;
        private int phase;
        private int currentIndex = -1;
        private int firstIndex;
        private boolean roundFinished;
        private boolean gameOver;
        private boolean revealFirstCards;
        private boolean rematchMode;

        // [추가] 기권승 여부 및 직전 승자 인덱스 저장 변수[cite: 33, 35]
        private boolean foldedWin;
        private int lastWinnerIndex = -1;

        GameSession(List<ClientHandler> clientList) {
            for (ClientHandler c : clientList)
                players.add(new ServerPlayer(c, c.name, INITIAL_MONEY));
            initialPlayerCount = players.size();
        }

        synchronized void startFirstRound() {
            round = 1;
            startRound();
        }

        synchronized void nextRound(ClientHandler requester) {
            if (!roundFinished || gameOver)
                return;
            round++;
            startRound();
        }

        private void startRound() {
            foldedWin = false; // [추가] 새 라운드 시작 시 기권승 플래그 초기화[cite: 33, 35]
            roundFinished = false;
            revealFirstCards = false;
            phase = 1;
            pending.clear();

            if (!rematchMode)
                pot = 0;

            if (!rematchMode) {
                for (ServerPlayer p : players) {
                    p.rematchTarget = !p.out;
                    if (!p.out && p.money <= 0)
                        p.out = true;
                }
            }

            int activeCount = countActive();
            if (activeCount <= 1) {
                finishWholeGame();
                return;
            }

            int eliminated = initialPlayerCount - activeCount;
            multiplier = eliminated <= 0 ? 1 : (long) Math.pow(3, eliminated);
            long entryFee = BASE_ENTRY_FEE * multiplier;

            for (ServerPlayer p : players) {
                p.folded = false;
                p.currentBet = 0;
                p.card1 = null;
                p.card2 = null;

                if (p.out || !p.rematchTarget)
                    continue;

                if (!rematchMode) {
                    if (p.money < entryFee) {
                        p.out = true;
                        p.rematchTarget = false;
                        broadcastLog(p.name + " 님이 입장료 부족으로 탈락했습니다.");
                    } else {
                        p.money -= entryFee;
                        p.currentBet = entryFee;
                        pot += entryFee;
                    }
                }
            }

            if (countRoundPlayers() <= 1) {
                finishWholeGame();
                return;
            }

            List<Card> deck = Card.createDeck();
            Collections.shuffle(deck);
            int cardIndex = 0;
            for (ServerPlayer p : players) {
                if (!p.out && p.rematchTarget) {
                    p.card1 = deck.get(cardIndex++);
                    p.card2 = deck.get(cardIndex++);
                }
            }

            // [수정] 라운드 선(firstIndex) 지정: 직전 승자가 존재하고 탈락하지 않았다면 직전 승자가 선[cite: 33, 35]
            if (round == 1 || lastWinnerIndex < 0 || players.get(lastWinnerIndex).out
                    || !players.get(lastWinnerIndex).rematchTarget) {
                firstIndex = findFirstRoundPlayer(round % players.size());
            } else {
                firstIndex = findFirstRoundPlayer(lastWinnerIndex);
            }

            targetBet = BASE_BET_UNIT * multiplier;
            fillPendingForPhase();
            currentIndex = findNextPending(firstIndex - 1);

            broadcastLog(round + "라운드 시작 (" + multiplier + "배) - " + phase + "차 베팅");
            broadcastState(this);
            announceTurn();
        }

        synchronized void action(ClientHandler client, int choice) {
            if (roundFinished || gameOver || currentIndex < 0)
                return;

            ServerPlayer p = players.get(currentIndex);
            if (p.client != client) {
                client.send("ERROR\t" + encode("지금은 다른 플레이어의 차례입니다."));
                return;
            }
            if (!pending.contains(currentIndex))
                return;
            if (choice < 1 || choice > 4)
                choice = 1;

            long needed = Math.max(0, targetBet - p.currentBet);
            boolean raised = false;

            if (choice == 2) {
                p.folded = true;
                pending.remove(currentIndex);
                broadcastLog(p.name + " : 다이");
            } else if (choice == 3) {
                long newTarget = Math.max(targetBet * 2, targetBet + BASE_BET_UNIT * multiplier);
                long amount = Math.max(0, newTarget - p.currentBet);
                amount = Math.min(amount, p.money);
                p.money -= amount;
                p.currentBet += amount;
                pot += amount;
                raised = p.currentBet > targetBet;
                if (raised)
                    targetBet = p.currentBet;
                broadcastLog(p.name + (p.money == 0 ? " : 더블 시도 후 올인 +" : " : 더블 +") + money(amount));
            } else if (choice == 4) {
                long amount = p.money;
                p.money = 0;
                p.currentBet += amount;
                pot += amount;
                raised = p.currentBet > targetBet;
                if (raised)
                    targetBet = p.currentBet;
                broadcastLog(p.name + " : 올인 +" + money(amount));
            } else {
                long amount = Math.min(needed, p.money);
                p.money -= amount;
                p.currentBet += amount;
                pot += amount;
                broadcastLog(p.name + " : 콜 +" + money(amount));
            }

            if (raised) {
                pending.clear();
                for (int i = 0; i < players.size(); i++) {
                    ServerPlayer other = players.get(i);
                    if (i != currentIndex && canAct(other))
                        pending.add(i);
                }
            } else {
                pending.remove(currentIndex);
            }

            if (countAlive() <= 1) {
                finishByFold();
                return;
            }

            if (pending.isEmpty()) {
                finishPhaseOrRound();
                return;
            }

            currentIndex = findNextPending(currentIndex);
            broadcastState(this);
            announceTurn();
        }

        synchronized void disconnect(ClientHandler client) {
            int index = indexOf(client);
            if (index < 0)
                return;

            ServerPlayer p = players.get(index);
            p.folded = true;
            p.out = true;
            p.rematchTarget = false;
            pending.remove(index);
            broadcastLog(p.name + " 님의 연결이 끊어져 게임에서 제외되었습니다.");

            if (roundFinished) {
                broadcastState(this);
                return;
            }
            if (countAlive() <= 1) {
                finishByFold();
                return;
            }
            if (pending.isEmpty()) {
                finishPhaseOrRound();
                return;
            }
            if (currentIndex == index) {
                currentIndex = findNextPending(index);
                broadcastState(this);
                announceTurn();
            } else {
                broadcastState(this);
            }
        }

        private void finishPhaseOrRound() {
            if (phase >= TOTAL_PHASES) {
                finishShowdown();
                return;
            }

            int finishedPhase = phase;
            phase++;
            if (finishedPhase == 2) {
                revealFirstCards = true;
                broadcastLog("2차 베팅 종료! 상대 카드가 한 장씩 공개됩니다.");
            }

            long maxBet = 0;
            for (ServerPlayer p : players)
                if (!p.out && p.rematchTarget && !p.folded)
                    maxBet = Math.max(maxBet, p.currentBet);
            targetBet = Math.max(maxBet, BASE_BET_UNIT * multiplier * phase);

            fillPendingForPhase();
            if (pending.isEmpty()) {
                // 남은 사람이 전부 올인인 경우는 더 입력받을 수 없으므로 바로 결과 처리합니다.
                finishShowdown();
                return;
            }
            currentIndex = findNextPending(firstIndex - 1);
            broadcastLog(finishedPhase + "차 베팅 종료. " + phase + "차 베팅 시작");
            broadcastState(this);
            announceTurn();
        }

        private void fillPendingForPhase() {
            pending.clear();
            for (int i = 0; i < players.size(); i++) {
                if (canAct(players.get(i)))
                    pending.add(i);
            }
        }

        private boolean canAct(ServerPlayer p) {
            return !p.out && p.rematchTarget && !p.folded && p.money > 0;
        }

        private int findNextPending(int fromIndex) {
            if (pending.isEmpty())
                return -1;
            for (int step = 1; step <= players.size(); step++) {
                int idx = Math.floorMod(fromIndex + step, players.size());
                if (pending.contains(idx))
                    return idx;
            }
            return -1;
        }

        private int findFirstRoundPlayer(int preferred) {
            for (int step = 0; step < players.size(); step++) {
                int idx = Math.floorMod(preferred + step, players.size());
                ServerPlayer p = players.get(idx);
                if (!p.out && p.rematchTarget)
                    return idx;
            }
            return 0;
        }

        private void announceTurn() {
            if (currentIndex < 0 || roundFinished)
                return;
            ServerPlayer p = players.get(currentIndex);
            long need = Math.max(0, targetBet - p.currentBet);
            broadcastLog("현재 차례: " + p.name + " (콜 금액 " + money(need) + ")");
        }

        private void finishByFold() {
            List<ServerPlayer> alive = alivePlayers();
            roundFinished = true;
            foldedWin = true; // [추가] 기권승 발생 시 패를 숨기기 위한 플래그[cite: 33, 35]
            revealFirstCards = false; // [수정] 기권 시 카드를 공개하지 않음[cite: 33, 35]
            pending.clear();
            currentIndex = -1;
            rematchMode = false;

            if (alive.size() == 1) {
                ServerPlayer winner = alive.get(0);
                winner.money += pot;
                lastWinnerIndex = players.indexOf(winner); // [추가] 기권승 승자를 다음 라운드 선으로 지정[cite: 33, 35]
                String summary = "기권승 발생";
                String result = "승자: " + winner.name + " (기권승, +" + money(pot) + ")";
                broadcastState(this);
                broadcastResult(summary, result);
                broadcastLog(result + " - 다음 라운드 버튼을 누르세요.");
            } else {
                broadcastState(this);
                broadcastResult("라운드 종료", "남은 플레이어가 없습니다.");
            }
        }

        private void finishShowdown() {
            List<ServerPlayer> contenders = alivePlayers();
            if (contenders.size() <= 1) {
                finishByFold();
                return;
            }

            List<Card.Hand> hands = new ArrayList<>();
            boolean hasGwangTarget = false;
            boolean hasDdangTarget = false;
            boolean hasGusa = false;
            boolean hasMungGusa = false;

            for (ServerPlayer p : contenders) {
                Card.Hand hand = Card.evaluate(p.card1, p.card2);
                hands.add(hand);
                if (hand.rank == Card.Rank.GWANG_DDANG_1318)
                    hasGwangTarget = true;
                if (hand.rank.ordinal() >= Card.Rank.DDANG_9.ordinal()
                        && hand.rank.ordinal() <= Card.Rank.DDANG_1.ordinal())
                    hasDdangTarget = true;
                if (hand.rank == Card.Rank.GUSA)
                    hasGusa = true;
                if (hand.rank == Card.Rank.MUNG_GUSA)
                    hasMungGusa = true;
            }

            List<Integer> scores = new ArrayList<>();
            int highestOtherScore = Integer.MIN_VALUE;
            for (Card.Hand hand : hands) {
                int score = hand.rank.score;
                if (hand.rank == Card.Rank.AMHAENG_EOSA && hasGwangTarget)
                    score = 290;
                if (hand.rank == Card.Rank.DDANG_JABI && hasDdangTarget)
                    score = 195;
                scores.add(score);
                if (hand.rank != Card.Rank.GUSA && hand.rank != Card.Rank.MUNG_GUSA)
                    highestOtherScore = Math.max(highestOtherScore, score);
            }
            if (highestOtherScore == Integer.MIN_VALUE)
                highestOtherScore = 0;

            String rematchReason = null;
            if (hasMungGusa && highestOtherScore <= 190)
                rematchReason = "멍텅구리 구사 발동";
            else if (hasGusa && highestOtherScore <= 90)
                rematchReason = "구사 발동";

            StringBuilder summary = new StringBuilder("결과 | ");
            for (int i = 0; i < contenders.size(); i++) {
                Card.Rank rank = hands.get(i).rank;
                String name = displayRank(rank, hasGwangTarget, hasDdangTarget, rematchReason != null);
                summary.append(contenders.get(i).name).append(" ").append(name);
                if (i < contenders.size() - 1)
                    summary.append(" · ");
            }

            roundFinished = true;
            revealFirstCards = true;
            pending.clear();
            currentIndex = -1;

            if (rematchReason != null) {
                rematchMode = true;
                for (ServerPlayer p : players)
                    p.rematchTarget = contenders.contains(p);
                broadcastState(this);
                broadcastResult(summary.toString(), rematchReason + " - 판돈 이월 후 재경기");
                broadcastLog("재경기입니다. 다음 라운드 버튼을 누르세요.");
                return;
            }

            rematchMode = false;
            for (ServerPlayer p : players)
                if (!p.out)
                    p.rematchTarget = true;

            int best = Integer.MIN_VALUE;
            List<Integer> winners = new ArrayList<>();
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) > best) {
                    best = scores.get(i);
                    winners.clear();
                    winners.add(i);
                } else if (scores.get(i) == best) {
                    winners.add(i);
                }
            }

            String result;
            if (winners.size() > 1) {
                long share = pot / winners.size();
                long remainder = pot % winners.size();
                StringBuilder names = new StringBuilder();
                for (int i = 0; i < winners.size(); i++) {
                    ServerPlayer p = contenders.get(winners.get(i));
                    p.money += share + (i == 0 ? remainder : 0);
                    if (i > 0)
                        names.append(", ");
                    names.append(p.name);
                }
                result = "동점: " + names + " - 팟 균등 분배";
            } else {
                int winnerIndex = winners.get(0);
                ServerPlayer winner = contenders.get(winnerIndex);
                winner.money += pot;

                // [추가] 쇼다운 단독 승자 발생 시 다음 라운드 선으로 지정[cite: 33, 35]
                lastWinnerIndex = players.indexOf(winner);

                String handName = displayRank(hands.get(winnerIndex).rank, hasGwangTarget, hasDdangTarget, false);
                result = "승자: " + winner.name + " (" + handName + ", +" + money(pot) + ")";
            }

            broadcastState(this);
            broadcastResult(summary.toString(), result);
            broadcastLog(result + " - 다음 라운드 버튼을 누르세요.");
        }

        private String displayRank(Card.Rank rank, boolean hasGwangTarget, boolean hasDdangTarget, boolean rematch) {
            if (rank == Card.Rank.AMHAENG_EOSA && !hasGwangTarget)
                return "1끗";
            if (rank == Card.Rank.DDANG_JABI && !hasDdangTarget)
                return "망통";
            if ((rank == Card.Rank.GUSA || rank == Card.Rank.MUNG_GUSA) && !rematch)
                return "3끗";
            return rank.name;
        }

        private void finishWholeGame() {
            gameOver = true;
            roundFinished = true;
            currentIndex = -1;
            pending.clear();
            ServerPlayer winner = null;
            for (ServerPlayer p : players)
                if (!p.out && p.money > 0)
                    winner = p;
            String result = winner == null ? "게임 종료 - 승자가 없습니다." : "최종 승자: " + winner.name;
            broadcastState(this);
            broadcastResult("게임 종료", result);
            broadcastLog(result);
        }

        private int countActive() {
            int count = 0;
            for (ServerPlayer p : players)
                if (!p.out)
                    count++;
            return count;
        }

        private int countRoundPlayers() {
            int count = 0;
            for (ServerPlayer p : players)
                if (!p.out && p.rematchTarget)
                    count++;
            return count;
        }

        private int countAlive() {
            return alivePlayers().size();
        }

        private List<ServerPlayer> alivePlayers() {
            List<ServerPlayer> result = new ArrayList<>();
            for (ServerPlayer p : players)
                if (!p.out && p.rematchTarget && !p.folded)
                    result.add(p);
            return result;
        }

        private int indexOf(ClientHandler client) {
            for (int i = 0; i < players.size(); i++)
                if (players.get(i).client == client)
                    return i;
            return -1;
        }

        private int currentTurnId() {
            if (currentIndex < 0 || currentIndex >= players.size())
                return -1;
            return players.get(currentIndex).client.id;
        }

        private String money(long value) {
            return String.format("%,d원", value);
        }
    }

    private static class ServerPlayer {
        final ClientHandler client;
        final String name;
        long money;
        long currentBet;
        boolean folded;
        boolean out;
        boolean rematchTarget = true;
        Card card1;
        Card card2;

        ServerPlayer(ClientHandler client, String name, long money) {
            this.client = client;
            this.name = name;
            this.money = money;
        }
    }

    private class ClientHandler implements Runnable {
        final Socket socket;
        final int id;
        BufferedReader in;
        PrintWriter out;
        String name;
        boolean host;
        boolean joined;
        volatile boolean closed;

        ClientHandler(Socket socket, int id) throws IOException {
            this.socket = socket;
            this.id = id;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        }

        @Override
        public void run() {
            try {
                String first = in.readLine();
                if (first == null || !first.startsWith("JOIN\t")) {
                    send("ERROR\t" + encode("잘못된 접속 요청입니다."));
                    return;
                }

                String encodedName = first.substring(5);
                String requestedName;
                try {
                    requestedName = SeutdaClient.decode(encodedName);
                } catch (Exception e) {
                    requestedName = "플레이어";
                }
                joined(this, requestedName);
                joined = true;

                String line;
                while (!closed && (line = in.readLine()) != null)
                    handleCommand(this, line);
            } catch (IOException ignored) {
            } finally {
                close();
                if (joined)
                    disconnected(this);
            }
        }

        synchronized void send(String message) {
            if (!closed && out != null)
                out.println(message);
        }

        synchronized void close() {
            if (closed)
                return;
            closed = true;
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    /** 서버 파일만 따로 실행해서 방을 열 수도 있습니다. */
    public static void main(String[] args) throws Exception {
        SeutdaServer server = new SeutdaServer();
        server.start();
        System.out.println("호스트 IP: " + getLocalIpAddress());
        System.out.println("클라이언트에서 위 IP와 포트 " + DEFAULT_PORT + "로 접속하세요.");
        while (server.isRunning())
            Thread.sleep(1000);
    }
}