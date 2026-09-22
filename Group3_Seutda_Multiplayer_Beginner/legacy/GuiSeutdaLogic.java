

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GuiSeutdaLogic {

    // =========================================================================
    // 1. UI 연동 인터페이스 (View Layer)
    //    UI 프레임워크(Swing, JavaFX 등)에서 이 인터페이스를 구현(implements)하여 사용합니다.
    // =========================================================================
    public interface GameUIListener {
        /** 라운드 정보 갱신 (ROUND 수, 현재 팟 상금, 기본 베팅금 등) */
        void onRoundHeaderUpdated(int round, boolean isRematch, String firstName, int activeCount, long currentMultiplier, long fee, long minBetUnit, long pot);
        
        /** 플레이어 상태 갱신 (잔액, 현재 베팅액, DIE 여부 등) */
        void onPlayerStatusUpdated(Player player);
        
        /** 플레이어 패 이미지/텍스트 갱신 */
        void onCardDealt(Player player, Card card1, Card card2, boolean revealSecondCard);
        
        /** 유저의 베팅 턴이 왔을 때 UI 버튼([콜], [다이], [더블], [올인]) 활성화/비활성화 */
        void onHumanTurnRequested(long neededToCall, long doubleAddAmount, long myChips);
        
        /** 누군가 베팅했을 때 말풍선/이펙트 연출 (예: "OOO : 더블! (+10,000원)") */
        void onBettingActionExecuted(Player player, String actionName, long addedAmount, long currentPot);
        
        /** 특수 상황(무승부, 구사 파토, 파산 알림 등) 팝업/토스트 메시지 연출 */
        void onGameMessage(String title, String message);
        
        /** 라운드 종료 및 승자 정산 연출 */
        void onRoundEnded(Player winner, long wonAmount, String rankName);
        
        /** 전체 게임 종료 (최후의 승자 발표) */
        void onGameOver(Player finalWinner);
    }

    // =========================================================================
    // 2. MODEL (게임 데이터 구조)
    // =========================================================================
    public static class Card {
        public final int month;       // 1 ~ 10
        public final boolean isKwang;
        public final boolean isYeol;

        public Card(int month, boolean isKwang, boolean isYeol) {
            this.month = month;
            this.isKwang = isKwang;
            this.isYeol = isYeol;
        }

        // [UI 연동 팁] 이 메서드로 UI에서 불러올 이미지 파일명을 생성합니다.
        public String getImagePath() {
            if (isKwang) return "cards/card_" + month + "_kwang.png";
            if (isYeol) return "cards/card_" + month + "_yeol.png";
            return "cards/card_" + month + "_normal.png";
        }
    }

    public enum Rank {
        G38_KWANG("38광땡"), AMHAENG_EOSA("암행어사"), GWANG_DDANG_1318("13/18광땡"), DDANG_JABI("땡잡이"),
        DDANG_10("장땡"), DDANG_9("9땡"), DDANG_8("8땡"), DDANG_7("7땡"), DDANG_6("6땡"),
        DDANG_5("5땡"), DDANG_4("4땡"), DDANG_3("3땡"), DDANG_2("2땡"), DDANG_1("1땡"),
        AL_LI("알리"), DOK_SA("독사"), GU_BING("구빙"), JANG_BING("장빙"), JANG_SA("장사"), SE_RYUK("세륙"),
        KKUT_9("9끗"), KKUT_8("8끗"), KKUT_7("7끗"), KKUT_6("6끗"), KKUT_5("5끗"),
        KKUT_4("4끗"), KKUT_3("3끗"), KKUT_2("2끗"), KKUT_1("1끗"),
        MUNG_GUSA("멍텅구리 구사"), GUSA("일반 구사"), MANG_TONG("망통");

        private final String name;
        Rank(String name) { this.name = name; }
        public String getName() { return name; }
    }

    public static class Player {
        public final String name;
        public final boolean isHuman;
        
        public Card card1;
        public Card card2;
        public Rank rank;
        
        public boolean isFolded = false;
        public boolean isOut = false;
        public boolean isRematchTarget = true;
        
        public long currentBet = 0;
        public long chips = 100_000_000L;

        public Player(String name, boolean isHuman) {
            this.name = name;
            this.isHuman = isHuman;
        }

        public void resetForNewRound() {
            this.card1 = null;
            this.card2 = null;
            this.rank = null;
            this.isFolded = false;
            this.currentBet = 0;
            this.isRematchTarget = true;
        }

        public void receiveCards(Card c1, Card c2) {
            this.card1 = c1;
            this.card2 = c2;
            calculateRank();
        }

        private void calculateRank() {
            int m1 = card1.month, m2 = card2.month;
            boolean k1 = card1.isKwang, k2 = card2.isKwang;
            boolean y1 = card1.isYeol, y2 = card2.isYeol;

            if ((m1 == 3 && k1 && m2 == 8 && k2) || (m1 == 8 && k1 && m2 == 3 && k2)) { rank = Rank.G38_KWANG; return; }
            if (k1 || k2) {
                if ((m1 == 1 && k1 && (m2 == 3 || m2 == 8)) || (m2 == 1 && k2 && (m1 == 3 || m1 == 8))) { rank = Rank.GWANG_DDANG_1318; return; }
            }
            if (m1 == m2) { rank = Rank.values()[Rank.DDANG_10.ordinal() + (10 - m1)]; return; }
            if (isMatch(m1, m2, 1, 2)) { rank = Rank.AL_LI; return; }
            if (isMatch(m1, m2, 1, 4)) { rank = Rank.DOK_SA; return; }
            if (isMatch(m1, m2, 1, 9)) { rank = Rank.GU_BING; return; }
            if (isMatch(m1, m2, 1, 10)) { rank = Rank.JANG_BING; return; }
            if (isMatch(m1, m2, 4, 10)) { rank = Rank.JANG_SA; return; }
            if (isMatch(m1, m2, 4, 6)) { rank = Rank.SE_RYUK; return; }
            if ((m1 == 4 && y1 && m2 == 7 && y2) || (m1 == 7 && y1 && m2 == 4 && y2)) { rank = Rank.AMHAENG_EOSA; return; }
            if (isMatch(m1, m2, 3, 7)) { rank = Rank.DDANG_JABI; return; }
            if ((m1 == 4 && y1 && m2 == 9 && y2) || (m1 == 9 && y1 && m2 == 4 && y2)) { rank = Rank.MUNG_GUSA; return; }
            if (isMatch(m1, m2, 4, 9)) { rank = Rank.GUSA; return; }

            int kkut = (m1 + m2) % 10;
            rank = (kkut > 0) ? Rank.values()[Rank.KKUT_9.ordinal() + (9 - kkut)] : Rank.MANG_TONG;
        }

        private boolean isMatch(int m1, int m2, int target1, int target2) {
            return (m1 == target1 && m2 == target2) || (m1 == target2 && m2 == target1);
        }
    }

    // =========================================================================
    // 3. CONTROLLER (게임 비즈니스 로직 및 UI 제어)
    // =========================================================================
    public static class SeutdaController {
        private final List<Player> players = new ArrayList<>();
        private final GameUIListener ui;

        private static final int INITIAL_PLAYERS_COUNT = 4;
        private static final long BASE_ENTRY_FEE = 1_000L;
        private static final long BASE_BET_UNIT = 10_000L;

        private long accumulatedPot = 0;
        private int roundCount = 1;
        private boolean isRematchMode = false;
        private long currentMultiplier = 1;
        private int firstPlayerIdx = 0;

        // 베팅 진행용 상태 변수
        private int currentPhase = 1; // 1차 베팅 or 2차 베팅
        private long targetCallAmount = 0;
        private int turnOffset = 0;
        private int lastRaiserOffset = -1;

        public SeutdaController(GameUIListener uiListener) {
            this.ui = uiListener;
            players.add(new Player("나 (Player)", true));
            players.add(new Player("플레이어 1", false));
            players.add(new Player("플레이어 2", false));
            players.add(new Player("플레이어 3", false));
        }

        /** [UI 연동] 게임 시작 버튼 클릭 시 호출 */
        public void startGame() {
            firstPlayerIdx = determineFirstPlayerByNightAndDay();
            startNewRound();
        }

        private void startNewRound() {
            checkAndProcessBankruptcies();
            currentMultiplier = updateMultiplierByEliminatedCount();

            List<Player> activeGamePlayers = getActiveGamePlayers();
            if (activeGamePlayers.size() <= 1) {
                ui.onGameOver(activeGamePlayers.isEmpty() ? null : activeGamePlayers.get(0));
                return;
            }

            while (players.get(firstPlayerIdx).isOut || !players.get(firstPlayerIdx).isRematchTarget) {
                firstPlayerIdx = (firstPlayerIdx + 1) % players.size();
            }

            long entryFee = BASE_ENTRY_FEE * currentMultiplier;
            long minBetUnit = BASE_BET_UNIT * currentMultiplier;

            ui.onRoundHeaderUpdated(roundCount, isRematchMode, players.get(firstPlayerIdx).name, 
                    activeGamePlayers.size(), currentMultiplier, entryFee, minBetUnit, accumulatedPot);

            // 입장료 처리
            for (Player p : players) {
                if (p.isOut) continue;

                if (!isRematchMode) p.resetForNewRound();
                else { p.isFolded = false; p.currentBet = 0; }

                if (!p.isRematchTarget) continue;

                if (!isRematchMode) {
                    if (p.chips < entryFee) {
                        p.isOut = true;
                        ui.onGameMessage("파산", p.name + " 님이 입장료 부족으로 탈락했습니다.");
                    } else {
                        p.chips -= entryFee;
                        accumulatedPot += entryFee;
                        p.currentBet += entryFee;
                    }
                }
                ui.onPlayerStatusUpdated(p);
            }

            checkAndProcessBankruptcies();
            if (countActivePlayers() <= 1) return;

            // 카드 분배 및 전달
            List<Card> deck = createDeck();
            Collections.shuffle(deck);

            int cardIdx = 0;
            for (int i = 0; i < players.size(); i++) {
                int pIdx = (firstPlayerIdx + i) % players.size();
                Player p = players.get(pIdx);
                if (!p.isOut && p.isRematchTarget) {
                    p.receiveCards(deck.get(cardIdx++), deck.get(cardIdx++));
                    // 1차 베팅 시점에는 2구패를 숨긴 상태로 UI에 분배
                    ui.onCardDealt(p, p.card1, p.card2, false);
                }
            }

            // 1차 베팅 시작
            currentPhase = 1;
            targetCallAmount = minBetUnit;
            turnOffset = 0;
            lastRaiserOffset = -1;
            
            processNextBetTurn();
        }

        /** 베팅 순서(턴)를 순차적으로 진행하는 내부 상태 머신 */
        private void processNextBetTurn() {
            if (countAlivePlayers() <= 1 || (lastRaiserOffset != -1 && turnOffset == lastRaiserOffset)) {
                endBettingPhase();
                return;
            }

            int currentIdx = (firstPlayerIdx + turnOffset) % players.size();
            Player p = players.get(currentIdx);

            if (p.isOut || !p.isRematchTarget || p.isFolded) {
                advanceTurn();
                return;
            }

            if (p.chips == 0) {
                // 올인 플레이어 자동 진행
                ui.onBettingActionExecuted(p, "올인 상태 (체크/콜)", 0, accumulatedPot);
                if (lastRaiserOffset == -1) lastRaiserOffset = turnOffset;
                advanceTurn();
                return;
            }

            long neededToCall = targetCallAmount - p.currentBet;

            if (p.isHuman) {
                long doubleTarget = targetCallAmount * 2;
                long doubleAddAmount = doubleTarget - p.currentBet;
                
                // [UI 연동 핵심] 유저의 입력을 받기 위해 UI 버튼을 활성화하고 컨트롤러는 대기 상태로 전환
                ui.onHumanTurnRequested(neededToCall, doubleAddAmount, p.chips);
            } else {
                // AI 턴 계산 및 자동 실행
                handleAIBetting(p, neededToCall);
                advanceTurn();
            }
        }

        /** [UI 연동 핵심] 유저가 UI 버튼(1:콜, 2:다이, 3:더블, 4:올인)을 클릭했을 때 호출되는 액션 메서드 */
        public void onHumanBetChoice(int choice) {
            int currentIdx = (firstPlayerIdx + turnOffset) % players.size();
            Player p = players.get(currentIdx);
            long neededToCall = targetCallAmount - p.currentBet;
            long doubleTarget = targetCallAmount * 2;
            long doubleAddAmount = doubleTarget - p.currentBet;

            if (choice == 2) { // 다이
                p.isFolded = true;
                ui.onBettingActionExecuted(p, "다이", 0, accumulatedPot);
            } else if (choice == 3) { // 더블
                if (p.chips <= doubleAddAmount) {
                    executeAllIn(p);
                } else {
                    targetCallAmount = doubleTarget;
                    p.currentBet += doubleAddAmount;
                    p.chips -= doubleAddAmount;
                    accumulatedPot += doubleAddAmount;
                    lastRaiserOffset = turnOffset;
                    ui.onBettingActionExecuted(p, "더블", doubleAddAmount, accumulatedPot);
                }
            } else if (choice == 4) { // 올인
                executeAllIn(p);
            } else { // 콜
                long payAmount = Math.min(neededToCall, p.chips);
                p.currentBet += payAmount;
                p.chips -= payAmount;
                accumulatedPot += payAmount;
                if (lastRaiserOffset == -1) lastRaiserOffset = turnOffset;
                ui.onBettingActionExecuted(p, "콜", payAmount, accumulatedPot);
            }

            ui.onPlayerStatusUpdated(p);
            advanceTurn();
        }

        private void executeAllIn(Player p) {
            long allInAmount = p.chips;
            p.currentBet += allInAmount;
            p.chips = 0;
            accumulatedPot += allInAmount;
            if (p.currentBet > targetCallAmount) {
                targetCallAmount = p.currentBet;
                lastRaiserOffset = turnOffset;
            }
            ui.onBettingActionExecuted(p, "올인", allInAmount, accumulatedPot);
        }

        private void advanceTurn() {
            turnOffset = (turnOffset + 1) % players.size();
            processNextBetTurn();
        }

        private void endBettingPhase() {
            if (countAlivePlayers() <= 1) {
                handleSingleWinner();
                return;
            }

            if (currentPhase == 1) {
                // 1차 베팅 종료 -> 1구패 오픈 및 2차 베팅 준비
                currentPhase = 2;
                for (Player p : players) {
                    if (!p.isOut && p.isRematchTarget && !p.isFolded) {
                        ui.onCardDealt(p, p.card1, p.card2, false); // UI에서 1구패 오픈 연출
                    }
                }

                long maxBet = 0;
                for (Player p : players) {
                    if (!p.isOut && p.isRematchTarget && !p.isFolded && p.currentBet > maxBet) {
                        maxBet = p.currentBet;
                    }
                }

                targetCallAmount = Math.max(maxBet, BASE_BET_UNIT * currentMultiplier * 2);
                turnOffset = 0;
                lastRaiserOffset = -1;
                processNextBetTurn();
            } else {
                // 2차 베팅까지 완전 종료 -> 패 최종 공개 및 승자 판정
                revealFinalCardsAndEvaluateWinner();
            }
        }

        private void revealFinalCardsAndEvaluateWinner() {
            for (Player p : players) {
                if (!p.isOut && p.isRematchTarget && !p.isFolded) {
                    ui.onCardDealt(p, p.card1, p.card2, true); // 2구패까지 모두 오픈 연출
                }
            }

            // 승자 계산 로직
            List<Player> alivePlayers = getAlivePlayers();
            Rank topRank = alivePlayers.get(0).rank;
            for (Player p : alivePlayers) {
                if (p.rank.ordinal() < topRank.ordinal()) topRank = p.rank;
            }

            List<Player> winners = new ArrayList<>();
            for (Player p : alivePlayers) {
                if (p.rank == topRank) winners.add(p);
            }

            if (winners.size() > 1) {
                ui.onGameMessage("무승부", topRank.getName() + " 동점자 발생! 판돈이 이월됩니다.");
                for (Player p : players) p.isRematchTarget = winners.contains(p);
                firstPlayerIdx = players.indexOf(winners.get(0));
                isRematchMode = true;
                roundCount++;
                startNewRound();
            } else {
                Player winner = winners.get(0);
                distributePotWithSidePot(alivePlayers, winner);
                ui.onRoundEnded(winner, accumulatedPot, winner.rank.getName());
                
                firstPlayerIdx = players.indexOf(winner);
                accumulatedPot = 0;
                roundCount++;
                isRematchMode = false;
                resetAllRematchTargets();

                startNewRound();
            }
        }

        private void handleSingleWinner() {
            Player winner = getAlivePlayers().get(0);
            winner.chips += accumulatedPot;
            ui.onRoundEnded(winner, accumulatedPot, "기권승");

            firstPlayerIdx = players.indexOf(winner);
            accumulatedPot = 0;
            roundCount++;
            isRematchMode = false;
            resetAllRematchTargets();

            startNewRound();
        }

        private void handleAIBetting(Player p, long neededToCall) {
            double rand = Math.random();
            int rankOrder = p.rank.ordinal();
            boolean aiAllIn = false, aiDouble = false, aiCall = false;

            if (currentPhase == 1) {
                if (rankOrder <= Rank.AL_LI.ordinal()) {
                    if (rand < 0.15) aiAllIn = true; else if (rand < 0.85) aiDouble = true; else aiCall = true;
                } else if (rankOrder <= Rank.KKUT_7.ordinal()) {
                    if (rand < 0.03) aiAllIn = true; else if (rand < 0.50) aiDouble = true; else aiCall = true;
                } else {
                    if (rand < 0.30) aiDouble = true; else if (rand < 0.80) aiCall = true;
                }
            } else {
                if (rankOrder <= Rank.DDANG_1.ordinal()) {
                    if (rand < 0.25) aiAllIn = true; else if (rand < 0.90) aiDouble = true; else aiCall = true;
                } else {
                    if (neededToCall == 0) aiCall = true; else if (rand < 0.10) aiDouble = true;
                }
            }

            if (aiAllIn) executeAllIn(p);
            else if (aiDouble) {
                long doubleTarget = targetCallAmount * 2;
                long doubleAddAmount = doubleTarget - p.currentBet;
                if (p.chips <= doubleAddAmount) executeAllIn(p);
                else {
                    targetCallAmount = doubleTarget;
                    p.currentBet += doubleAddAmount;
                    p.chips -= doubleAddAmount;
                    accumulatedPot += doubleAddAmount;
                    lastRaiserOffset = turnOffset;
                    ui.onBettingActionExecuted(p, "더블", doubleAddAmount, accumulatedPot);
                }
            } else if (aiCall || neededToCall == 0) {
                long payAmount = Math.min(neededToCall, p.chips);
                p.currentBet += payAmount;
                p.chips -= payAmount;
                accumulatedPot += payAmount;
                if (lastRaiserOffset == -1) lastRaiserOffset = turnOffset;
                ui.onBettingActionExecuted(p, "콜", payAmount, accumulatedPot);
            } else {
                p.isFolded = true;
                ui.onBettingActionExecuted(p, "다이", 0, accumulatedPot);
            }
            ui.onPlayerStatusUpdated(p);
        }

        private int determineFirstPlayerByNightAndDay() {
            int currentHour = LocalTime.now().getHour();
            boolean isNight = (currentHour >= 18 || currentHour < 6);
            List<Player> candidates = getActiveGamePlayers();

            while (true) {
                List<Card> deck = createDeck();
                Collections.shuffle(deck);
                int targetMonth = isNight ? 11 : 0;
                List<Player> topCandidates = new ArrayList<>();

                for (int i = 0; i < candidates.size(); i++) {
                    Player p = candidates.get(i);
                    Card picked = deck.get(i);
                    boolean isBetter = isNight ? (picked.month < targetMonth) : (picked.month > targetMonth);
                    if (isBetter) {
                        targetMonth = picked.month;
                        topCandidates.clear();
                        topCandidates.add(p);
                    } else if (picked.month == targetMonth) {
                        topCandidates.add(p);
                    }
                }

                if (topCandidates.size() == 1) {
                    return players.indexOf(topCandidates.get(0));
                }
                candidates = topCandidates;
            }
        }

        private void distributePotWithSidePot(List<Player> alivePlayers, Player winner) {
            long winnerMaxClaimPerPlayer = winner.currentBet;
            long winnerEarned = 0;

            for (Player p : players) {
                long contribution = Math.min(p.currentBet, winnerMaxClaimPerPlayer);
                winnerEarned += contribution;
            }

            winner.chips += winnerEarned;
            long remainingPot = accumulatedPot - winnerEarned;

            if (remainingPot > 0) {
                List<Player> sidePotEligible = new ArrayList<>(alivePlayers);
                sidePotEligible.remove(winner);
                sidePotEligible.sort(Comparator.comparing((Player p) -> p.rank.ordinal()).thenComparing((Player p) -> -p.currentBet));

                if (!sidePotEligible.isEmpty()) {
                    sidePotEligible.get(0).chips += remainingPot;
                } else {
                    winner.chips += remainingPot;
                }
            }
        }

        private void checkAndProcessBankruptcies() {
            for (Player p : players) {
                if (!p.isOut && p.chips <= 0) {
                    p.isOut = true;
                    p.isRematchTarget = false;
                }
            }
        }

        private long updateMultiplierByEliminatedCount() {
            int eliminatedCount = INITIAL_PLAYERS_COUNT - countActivePlayers();
            if (eliminatedCount <= 0) return 1;
            return (long) Math.pow(3, eliminatedCount);
        }

        private List<Card> createDeck() {
            List<Card> deck = new ArrayList<>(20);
            for (int month = 1; month <= 10; month++) {
                boolean isKwang = (month == 1 || month == 3 || month == 8);
                boolean isYeol = (month == 4 || month == 7 || month == 9);
                deck.add(new Card(month, isKwang, isYeol));
                deck.add(new Card(month, false, false));
            }
            return deck;
        }

        private List<Player> getActiveGamePlayers() {
            List<Player> active = new ArrayList<>();
            for (Player p : players) if (!p.isOut) active.add(p);
            return active;
        }

        private List<Player> getAlivePlayers() {
            List<Player> alive = new ArrayList<>();
            for (Player p : players) if (!p.isOut && p.isRematchTarget && !p.isFolded) alive.add(p);
            return alive;
        }

        private int countActivePlayers() { return getActiveGamePlayers().size(); }
        private int countAlivePlayers() { return getAlivePlayers().size(); }
        private void resetAllRematchTargets() { for (Player p : players) p.isRematchTarget = true; }
    }
}