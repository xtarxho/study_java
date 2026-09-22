package sutda;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** 섯다의 라운드 진행, 베팅, AI, 승패 판정을 담당합니다. */
public class Game {
    private static final int INITIAL_PLAYERS_COUNT = 4;
    private static final long INITIAL_MONEY = 100_000_000L;
    private static final long BASE_ENTRY_FEE = 1_000L;
    private static final long BASE_BET_UNIT = 10_000L;
    private static final int TOTAL_BET_PHASES = 4;

    private final Random random = new Random();
    final List<Player> players = new ArrayList<>();

    long accumulatedPot = 0;
    private int roundCount = 1;
    private boolean rematchMode = false;
    private long currentMultiplier = 1;
    private int firstPlayerIdx = 0;

    private int currentPhase = 1;
    private long targetCallAmount = 0;
    private int turnOffset = 0;
    private int lastRaiserOffset = -1;

    private boolean roundFinished = false;
    boolean waitingForAction = false;

    private final GameEventListener listener;

    /** 화면에서 필요한 변화만 전달하기 위한 이벤트입니다. */
    public interface GameEventListener {
        void onStateChanged();

        void onStatusMessage(String message);

        void onRoundFinished(String resultSummary, String resultMessage);

        void onMidRoundCardReveal();
    }

    /** 화면과 게임 로직을 연결합니다. */
    public Game(GameEventListener listener) {
        this.listener = listener;
    }

    /** 새 게임을 만들고 첫 라운드를 시작합니다. */
    public void initGame(String playerName) {
        players.clear();
        accumulatedPot = 0;
        roundCount = 1;
        rematchMode = false;
        roundFinished = false;
        waitingForAction = false;
        firstPlayerIdx = 0; // 게임 시작 시 첫 플레이어(유저)가 선

        players.add(new Player(playerName, true, INITIAL_MONEY));
        players.add(new Player("플레이어 1", false, INITIAL_MONEY));
        players.add(new Player("플레이어 2", false, INITIAL_MONEY));
        players.add(new Player("플레이어 3", false, INITIAL_MONEY));

        startNewRound();
    }

    /** 현재 판이 끝난 뒤 다음 라운드로 넘어갑니다. */
    public void nextRound() {
        if (!roundFinished)
            return;
        roundCount++;
        startNewRound();
    }

    /** 입장료, 카드 분배, 베팅 상태를 준비하고 한 라운드를 시작합니다. */
    private void startNewRound() {
        roundFinished = false;
        waitingForAction = false;
        if (!rematchMode)
            accumulatedPot = 0;

        // 일반 라운드에서는 돈이 없는(0원 이하) 플레이어를 탈락 처리합니다.
        for (Player p : players) {
            if (!p.out && p.money <= 0) {
                p.out = true;
                p.rematchTarget = false;
                if (listener != null)
                    listener.onStatusMessage(p.name + " 님의 보유금이 0원이 되어 방에서 나갑니다.");
            }
        }

        int activeCount = 0;
        Player finalWinner = null;
        for (Player p : players) {
            if (!p.out) {
                activeCount++;
                finalWinner = p;
            }
        }

        if (activeCount <= 1) {
            roundFinished = true;
            if (listener != null) {
                listener.onRoundFinished("게임 종료", finalWinner == null ? "무승부" : "최후의 승자: " + finalWinner.name);
            }
            return;
        }

        int eliminatedCount = INITIAL_PLAYERS_COUNT - activeCount;
        currentMultiplier = eliminatedCount == 0 ? 1 : (long) Math.pow(3, eliminatedCount);

        // 재경기가 아닐 때, 현재 지정된 firstPlayerIdx가 탈락했거나 유효하지 않다면 살아있는 다음 플레이어로 보정
        if (!rematchMode) {
            if (firstPlayerIdx < 0 || firstPlayerIdx >= players.size() || players.get(firstPlayerIdx).out) {
                int idx = 0;
                while (idx < players.size() && players.get(idx).out) {
                    idx++;
                }
                if (idx < players.size()) {
                    firstPlayerIdx = idx;
                }
            }
        }

        long entryFee = BASE_ENTRY_FEE * currentMultiplier;
        long minBetUnit = BASE_BET_UNIT * currentMultiplier;

        for (Player p : players) {
            if (p.out)
                continue;
            if (!rematchMode)
                p.rematchTarget = true;
            p.folded = false;
            p.currentBet = 0;

            if (!p.rematchTarget)
                continue;

            if (!rematchMode) {
                if (p.money < entryFee) {
                    p.money = 0;
                    p.out = true;
                    p.rematchTarget = false;
                    if (listener != null)
                        listener.onStatusMessage(p.name + " 님이 입장료 부족으로 방에서 나갑니다.");
                } else {
                    p.money -= entryFee;
                    p.currentBet = entryFee;
                    accumulatedPot += entryFee;
                }
            }
        }

        for (Player p : players) {
            if (!p.out && p.money <= 0) {
                p.out = true;
                p.rematchTarget = false;
                if (listener != null)
                    listener.onStatusMessage(p.name + " 님의 보유금이 0원이 되어 방에서 나갑니다.");
            }
        }

        List<Card> deck = Card.createDeck();
        Collections.shuffle(deck);
        int cardIdx = 0;
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get((firstPlayerIdx + i) % players.size());
            if (!p.out && p.rematchTarget) {
                p.resetRound(deck.get(cardIdx++), deck.get(cardIdx++));
            }
        }

        currentPhase = 1;
        targetCallAmount = minBetUnit;
        turnOffset = 0;
        lastRaiserOffset = -1;

        if (listener != null) {
            listener.onStateChanged();
            listener.onStatusMessage(roundCount + "라운드 시작 (배율: " + currentMultiplier + "배) - 선: " + players.get(firstPlayerIdx).name);
        }
        processNextBetTurn();
    }

    /** 현재 차례를 처리합니다. AI 행동과 베팅 차수 전환도 이 메서드에서 함께 처리합니다. */
    private void processNextBetTurn() {
        if (roundFinished)
            return;

        int aliveCount = 0;
        Player onlyPlayer = null;
        for (Player p : players) {
            if (!p.out && p.rematchTarget && !p.folded) {
                aliveCount++;
                onlyPlayer = p;
            }
        }

        if (aliveCount <= 1) {
            roundFinished = true;
            if (onlyPlayer != null) {
                onlyPlayer.money += accumulatedPot;
                int winnerIdx = players.indexOf(onlyPlayer);
                if (winnerIdx != -1) {
                    firstPlayerIdx = winnerIdx; // 기권승 시에도 승자가 다음 선이 되도록 지정
                }
            }
            rematchMode = false;
            for (Player p : players)
                if (!p.out)
                    p.rematchTarget = true;
            if (listener != null) {
                listener.onRoundFinished("기권승 발생", onlyPlayer == null ? "무승부" : "🏆 승자: " + onlyPlayer.name + " (기권승)");
            }
            return;
        }

        if (lastRaiserOffset != -1 && turnOffset == lastRaiserOffset) {
            if (currentPhase < TOTAL_BET_PHASES) {
                int finishedPhase = currentPhase++;
                long maxBet = 0;
                for (Player p : players) {
                    if (!p.out && p.rematchTarget && !p.folded)
                        maxBet = Math.max(maxBet, p.currentBet);
                }
                targetCallAmount = Math.max(maxBet, BASE_BET_UNIT * currentMultiplier * currentPhase);
                turnOffset = 0;
                lastRaiserOffset = -1;

                if (listener != null) {
                    listener.onStatusMessage(finishedPhase + "차 베팅 종료. " + currentPhase + "차 베팅을 시작합니다.");
                    if (finishedPhase == 2) {
                        listener.onStatusMessage("2차 베팅 종료! 생존한 상대 패가 한 장씩 공개됩니다.");
                        listener.onMidRoundCardReveal();
                    }
                }
                processNextBetTurn();
            } else {
                finishRound();
            }
            return;
        }

        int currentIdx = (firstPlayerIdx + turnOffset) % players.size();
        Player p = players.get(currentIdx);

        if (p.out || !p.rematchTarget || p.folded) {
            turnOffset = (turnOffset + 1) % players.size();
            processNextBetTurn();
            return;
        }

        if (p.money <= 0) {
            p.money = 0;
            if (listener != null)
                listener.onStatusMessage(p.name + " : 올인 상태 (자동 통과)");
            if (lastRaiserOffset == -1)
                lastRaiserOffset = turnOffset;
            turnOffset = (turnOffset + 1) % players.size();
            processNextBetTurn();
            return;
        }

        long neededToCall = Math.max(0, targetCallAmount - p.currentBet);

        if (p.human) {
            waitingForAction = true;
            if (listener != null) {
                listener.onStateChanged();
                listener.onStatusMessage("당신의 차례입니다. (콜 금액: " + String.format("%,d원", neededToCall) + ")");
            }
            return;
        }

        waitingForAction = false;
        if (listener != null)
            listener.onStatusMessage(p.name + " 생각 중...");

        Thread aiThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (roundFinished)
                            return;

                        double rand = random.nextDouble();
                        int rankScore = Card.evaluate(p.card1, p.card2).rank.score;

                        boolean fold;
                        boolean allIn;
                        boolean doubleBet;

                        if (rankScore < 80) {
                            fold = rand < 0.35;
                            doubleBet = !fold && (rand < 0.45);
                            allIn = false;
                        } else if (rankScore < 150) {
                            fold = rand < 0.10;
                            doubleBet = !fold && (rand < 0.30);
                            allIn = false;
                        } else {
                            fold = false;
                            allIn = rankScore >= 200 && rand < 0.45;
                            doubleBet = !allIn;
                        }

                        if (fold) {
                            p.folded = true;
                            if (listener != null)
                                listener.onStatusMessage(p.name + " : 다이");
                        } else if (allIn) {
                            long amount = p.money; // 가진 돈 전부
                            p.money = 0;
                            p.currentBet += amount;
                            accumulatedPot += amount;
                            
                            // 사이드팟/상대방 자산 한도에 따른 초과분 환불 처리 안전 장치
                            long maxOpponentBet = getMaxOpponentCapacity(p);
                            if (p.currentBet > maxOpponentBet) {
                                long refund = p.currentBet - maxOpponentBet;
                                if (refund > 0) {
                                    p.currentBet -= refund;
                                    accumulatedPot -= refund;
                                    p.money += refund;
                                    if (listener != null)
                                        listener.onStatusMessage(p.name + " 님의 초과 금액 " + String.format("%,d원", refund) + "이 되돌아옵니다.");
                                }
                            }
                            targetCallAmount = Math.max(targetCallAmount, p.currentBet);
                            lastRaiserOffset = turnOffset;

                            if (listener != null)
                                listener.onStatusMessage(p.name + " : 올인!");
                        } else if (doubleBet) {
                            long doubleTarget = targetCallAmount * 2;
                            long idealAmount = doubleTarget - p.currentBet;
                            long amount = Math.min(idealAmount, p.money); // 보유 자산을 절대 넘지 않도록 제한

                            long maxOpponentBet = getMaxOpponentCapacity(p);
                            if (p.currentBet + amount > maxOpponentBet && maxOpponentBet > p.currentBet) {
                                amount = maxOpponentBet - p.currentBet;
                                targetCallAmount = maxOpponentBet;
                            }

                            p.money -= amount;
                            p.currentBet += amount;
                            accumulatedPot += amount;

                            if (p.money == 0) {
                                lastRaiserOffset = turnOffset;
                                if (listener != null)
                                    listener.onStatusMessage(p.name + " : 올인!");
                            } else if (amount > neededToCall) {
                                targetCallAmount = p.currentBet;
                                lastRaiserOffset = turnOffset;
                                if (listener != null)
                                    listener.onStatusMessage(p.name + " : 더블!");
                            } else {
                                if (lastRaiserOffset == -1)
                                    lastRaiserOffset = turnOffset;
                                if (listener != null)
                                    listener.onStatusMessage(p.name + " : 콜!");
                            }
                        } else {
                            long amount = Math.min(neededToCall, p.money);
                            p.money -= amount;
                            p.currentBet += amount;
                            accumulatedPot += amount;
                            if (lastRaiserOffset == -1)
                                lastRaiserOffset = turnOffset;
                            if (listener != null)
                                listener.onStatusMessage(p.name + " : 콜!");
                        }

                        if (p.money < 0) p.money = 0;

                        if (listener != null)
                            listener.onStateChanged();
                        turnOffset = (turnOffset + 1) % players.size();
                        processNextBetTurn();
                    }
                });
            }
        });
        aiThread.setDaemon(true);
        aiThread.start();
    }

    /** 상대방들이 감당할 수 있는 최대 베팅 한도를 계산합니다. */
    private long getMaxOpponentCapacity(Player actor) {
        long max = 0;
        boolean hasOther = false;
        for (Player p : players) {
            if (!p.out && p.rematchTarget && !p.folded && p != actor) {
                hasOther = true;
                max = Math.max(max, p.currentBet + p.money);
            }
        }
        return hasOther ? max : actor.currentBet + actor.money;
    }

    /** 사용자가 선택한 콜, 다이, 더블, 올인을 처리합니다. */
    public void humanAction(int choice) {
        if (roundFinished || !waitingForAction)
            return;
        waitingForAction = false;

        Player p = players.get((firstPlayerIdx + turnOffset) % players.size());
        long neededToCall = Math.max(0, targetCallAmount - p.currentBet);

        if (choice == 2) {
            p.folded = true;
            if (listener != null)
                listener.onStatusMessage("나는 다이했습니다.");
        } else if (choice == 3) {
            long doubleTarget = targetCallAmount * 2;
            long idealAmount = doubleTarget - p.currentBet;
            long amount = Math.min(idealAmount, p.money); // 잔액 초과 방지

            long maxOpponentBet = getMaxOpponentCapacity(p);
            if (p.currentBet + amount > maxOpponentBet && maxOpponentBet > p.currentBet) {
                amount = maxOpponentBet - p.currentBet;
                targetCallAmount = maxOpponentBet;
            }

            p.money -= amount;
            p.currentBet += amount;
            accumulatedPot += amount;

            if (p.money == 0) {
                lastRaiserOffset = turnOffset;
                if (listener != null)
                    listener.onStatusMessage("나는 올인!");
            } else if (amount > neededToCall) {
                targetCallAmount = p.currentBet;
                lastRaiserOffset = turnOffset;
                if (listener != null)
                    listener.onStatusMessage("나는 더블! +" + String.format("%,d원", amount));
            } else {
                if (lastRaiserOffset == -1)
                    lastRaiserOffset = turnOffset;
                if (listener != null)
                    listener.onStatusMessage("나는 콜! +" + String.format("%,d원", amount));
            }
        } else if (choice == 4) {
            long amount = p.money;
            p.money = 0;
            p.currentBet += amount;
            accumulatedPot += amount;
            
            long maxOpponentBet = getMaxOpponentCapacity(p);
            if (p.currentBet > maxOpponentBet) {
                long refund = p.currentBet - maxOpponentBet;
                if (refund > 0) {
                    p.currentBet -= refund;
                    accumulatedPot -= refund;
                    p.money += refund;
                    if (listener != null) {
                        listener.onStatusMessage("상대방 자산 한도를 초과하여 차액 " + String.format("%,d원", refund) + "이 되돌아옵니다.");
                    }
                }
            }
            targetCallAmount = Math.max(targetCallAmount, p.currentBet);
            lastRaiserOffset = turnOffset;

            if (listener != null)
                listener.onStatusMessage("나는 올인!");
        } else {
            long amount = Math.min(neededToCall, p.money);
            p.money -= amount;
            p.currentBet += amount;
            accumulatedPot += amount;
            if (lastRaiserOffset == -1)
                lastRaiserOffset = turnOffset;
            if (listener != null)
                listener.onStatusMessage("나는 콜! +" + String.format("%,d원", amount));
        }

        if (p.money < 0) p.money = 0;

        if (listener != null)
            listener.onStateChanged();
        turnOffset = (turnOffset + 1) % players.size();
        processNextBetTurn();
    }

    /** 마지막까지 남은 플레이어의 족보를 비교하고 상금 또는 재경기를 처리합니다. */
    private void finishRound() {
        roundFinished = true;

        List<Player> contenders = new ArrayList<>();
        for (Player p : players) {
            if (!p.out && p.rematchTarget && !p.folded)
                contenders.add(p);
        }

        if (contenders.isEmpty()) {
            if (listener != null)
                listener.onRoundFinished("결과 없음", "무승부");
            return;
        }

        List<Card.Hand> hands = new ArrayList<>();
        boolean hasGwangTarget = false;
        boolean hasDdangTarget = false;
        boolean hasGusa = false;
        boolean hasMungGusa = false;

        for (Player p : contenders) {
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
            if (hand.rank != Card.Rank.GUSA && hand.rank != Card.Rank.MUNG_GUSA) {
                highestOtherScore = Math.max(highestOtherScore, score);
            }
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
            String name = rank.name;
            if (rank == Card.Rank.AMHAENG_EOSA && !hasGwangTarget)
                name = "1끗";
            else if (rank == Card.Rank.DDANG_JABI && !hasDdangTarget)
                name = "망통";
            else if ((rank == Card.Rank.GUSA || rank == Card.Rank.MUNG_GUSA) && rematchReason == null)
                name = "3끗";
            summary.append(contenders.get(i).name).append(" ").append(name).append(" · ");
        }

        if (rematchReason != null) {
            rematchMode = true;
            for (Player p : players)
                p.rematchTarget = contenders.contains(p);
            if (listener != null)
                listener.onRoundFinished(summary.toString(), "🔁 " + rematchReason + " - 판돈 이월 후 재경기");
            return;
        }

        int bestScore = Integer.MIN_VALUE;
        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) > bestScore) {
                bestScore = scores.get(i);
                winners.clear();
                winners.add(i);
            } else if (scores.get(i) == bestScore) {
                winners.add(i);
            }
        }

        rematchMode = false;
        for (Player p : players)
            if (!p.out)
                p.rematchTarget = true;

        if (winners.size() > 1) {
            long share = accumulatedPot / winners.size();
            long remainder = accumulatedPot % winners.size();
            StringBuilder names = new StringBuilder();
            for (int i = 0; i < winners.size(); i++) {
                Player p = contenders.get(winners.get(i));
                p.money += share + (i == 0 ? remainder : 0);
                if (i > 0)
                    names.append(", ");
                names.append(p.name);
            }
            
            // 동점 승리 시 첫 번째 승자를 다음 라운드 선으로 지정
            int globalWinnerIdx = players.indexOf(contenders.get(winners.get(0)));
            if (globalWinnerIdx != -1) {
                firstPlayerIdx = globalWinnerIdx;
            }

            if (listener != null)
                listener.onRoundFinished(summary.toString(), "🤝 동점: " + names + " - 팟 균등 분배");
        } else {
            int winnerIndex = winners.get(0);
            Player winner = contenders.get(winnerIndex);
            winner.money += accumulatedPot;
            
            // 단독 승자의 인덱스를 찾아 다음 라운드 선(firstPlayerIdx)으로 확실하게 지정
            int globalWinnerIdx = players.indexOf(winner);
            if (globalWinnerIdx != -1) {
                firstPlayerIdx = globalWinnerIdx;
            }

            String handName = hands.get(winnerIndex).rank.name;
            if (hands.get(winnerIndex).rank == Card.Rank.AMHAENG_EOSA && !hasGwangTarget)
                handName = "1끗";
            if (hands.get(winnerIndex).rank == Card.Rank.DDANG_JABI && !hasDdangTarget)
                handName = "망통";
            if (listener != null)
                listener.onRoundFinished(summary.toString(), "🏆 승자: " + winner.name + " (" + handName + ")");
        }

        for (Player p : players) {
            if (!p.out && p.money <= 0) {
                p.money = 0;
                p.out = true;
                p.rematchTarget = false;
                if (listener != null)
                    listener.onStatusMessage(p.name + " 님의 보유금이 0원이 되어 방에서 나갑니다.");
            }
        }
    }
}