

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner; // [DELETE/REMOVE] UI 이식 시 제거 (더 이상 Scanner 입력을 사용하지 않음)

/**
 * 4인(사용자 1명 + AI 3명) 한국 전통 섯다 게임 메인 클래스.
 */
public class ConsoleSeutda {

    // [DELETE/REMOVE] 콘솔 입력을 위한 객체이므로 GUI 적용 시 삭제
    private static final Scanner SCANNER = new Scanner(System.in);

    // [MODEL/LOGIC] 게임 기본 설정 상수 (UI 환경에서도 동일하게 유지)
    private static final int INITIAL_PLAYERS_COUNT = 4;
    private static final long BASE_ENTRY_FEE = 1_000L; // 기본 입장료 (1천원)
    private static final long BASE_BET_UNIT = 10_000L; // 기본 베팅 단위 (1만원)

    // [DELETE/REMOVE] UI에서는 딜레이 대신 애니메이션/타이머(Timer, Thread)나 이벤트 제어로 대체되므로 삭제
    private static final int DELAY_MS = 1500;

    /**
     * [MODEL] 화투 패 1장의 상태 클래스
     * - UI 연동 시 Card 객체의 정보를 통해 이미지 파일(예: "card_1_kwang.png")을 렌더링하도록 확장 가능
     */
    static class Card {
        final int month; // 화투 월 (1 ~ 10)
        final boolean isKwang; // 광 여부
        final boolean isYeol; // 열끗 여부

        public Card(int month, boolean isKwang, boolean isYeol) {
            this.month = month;
            this.isKwang = isKwang;
            this.isYeol = isYeol;
        }

        // [DELETE/REMOVE] 콘솔용 텍스트 반환 메서드 (UI에서는 카드 이미지 매핑 로직으로 대체)
        @Override
        public String toString() {
            if (isKwang)
                return month + "광";
            if (isYeol)
                return month + "열";
            return String.valueOf(month);
        }
    }

    /**
     * [MODEL] 섯다 족보 서열
     */
    enum Rank {
        G38_KWANG("38광땡"),
        AMHAENG_EOSA("암행어사"),
        GWANG_DDANG_1318("13/18광땡"),
        DDANG_JABI("땡잡이"),
        DDANG_10("장땡"), DDANG_9("9땡"), DDANG_8("8땡"), DDANG_7("7땡"), DDANG_6("6땡"),
        DDANG_5("5땡"), DDANG_4("4땡"), DDANG_3("3땡"), DDANG_2("2땡"), DDANG_1("1땡"),
        AL_LI("알리"), DOK_SA("독사"), GU_BING("구빙"), JANG_BING("장빙"), JANG_SA("장사"), SE_RYUK("세륙"),
        KKUT_9("9끗"), KKUT_8("8끗"), KKUT_7("7끗"), KKUT_6("6끗"), KKUT_5("5끗"),
        KKUT_4("4끗"), KKUT_3("3끗"), KKUT_2("2끗"), KKUT_1("1끗"),
        MUNG_GUSA("멍텅구리 구사"),
        GUSA("일반 구사"),
        MANG_TONG("망통");

        private final String name;

        Rank(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * [MODEL] 플레이어 상태 관리 클래스
     * - UI 연동 시 이 클래스의 변수 변경에 맞춰 화면(프로필, 잔액 Label, 카드 View)을 갱신함
     */
    static class Player {
        final String name;
        final boolean isHuman;

        Card card1; // [UI-BIND] 화면의 플레이어 1구패 이미지 갱신
        Card card2; // [UI-BIND] 화면의 플레이어 2구패 이미지 갱신
        Rank rank; // [UI-BIND] 족보 텍스트 UI 갱신

        boolean isFolded = false; // [UI-BIND] True 시 화면에 "DIE" 뱃지 표시
        boolean isOut = false; // [UI-BIND] True 시 화면에서 아웃(비활성화) 처리
        boolean isRematchTarget = true;

        long currentBet = 0; // [UI-BIND] 베팅금 표시 UI 갱신
        long chips = 100_000_000L; // [UI-BIND] 보유 자산 Label 갱신

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

        // [MODEL/LOGIC] 족보 판정 로직
        private void calculateRank() {
            int m1 = card1.month;
            int m2 = card2.month;
            boolean k1 = card1.isKwang;
            boolean k2 = card2.isKwang;
            boolean y1 = card1.isYeol;
            boolean y2 = card2.isYeol;

            if ((m1 == 3 && k1 && m2 == 8 && k2) || (m1 == 8 && k1 && m2 == 3 && k2)) {
                rank = Rank.G38_KWANG;
                return;
            }

            if (k1 || k2) {
                if ((m1 == 1 && k1 && (m2 == 3 || m2 == 8)) || (m2 == 1 && k2 && (m1 == 3 || m1 == 8))) {
                    rank = Rank.GWANG_DDANG_1318;
                    return;
                }
            }

            if (m1 == m2) {
                rank = Rank.values()[Rank.DDANG_10.ordinal() + (10 - m1)];
                return;
            }

            if (isMatch(m1, m2, 1, 2)) {
                rank = Rank.AL_LI;
                return;
            }
            if (isMatch(m1, m2, 1, 4)) {
                rank = Rank.DOK_SA;
                return;
            }
            if (isMatch(m1, m2, 1, 9)) {
                rank = Rank.GU_BING;
                return;
            }
            if (isMatch(m1, m2, 1, 10)) {
                rank = Rank.JANG_BING;
                return;
            }
            if (isMatch(m1, m2, 4, 10)) {
                rank = Rank.JANG_SA;
                return;
            }
            if (isMatch(m1, m2, 4, 6)) {
                rank = Rank.SE_RYUK;
                return;
            }

            if ((m1 == 4 && y1 && m2 == 7 && y2) || (m1 == 7 && y1 && m2 == 4 && y2)) {
                rank = Rank.AMHAENG_EOSA;
                return;
            }

            if (isMatch(m1, m2, 3, 7)) {
                rank = Rank.DDANG_JABI;
                return;
            }

            if ((m1 == 4 && y1 && m2 == 9 && y2) || (m1 == 9 && y1 && m2 == 4 && y2)) {
                rank = Rank.MUNG_GUSA;
                return;
            }

            if (isMatch(m1, m2, 4, 9)) {
                rank = Rank.GUSA;
                return;
            }

            int kkut = (m1 + m2) % 10;
            if (kkut > 0) {
                rank = Rank.values()[Rank.KKUT_9.ordinal() + (9 - kkut)];
            } else {
                rank = Rank.MANG_TONG;
            }
        }

        private boolean isMatch(int m1, int m2, int target1, int target2) {
            return (m1 == target1 && m2 == target2) || (m1 == target2 && m2 == target1);
        }
    }

    // [DELETE/REMOVE] GUI에서는 Thread.sleep() 대신 UI 스레드 비동기 타이머나 애니메이션 완료 이벤트 사용
    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        // [MODEL] 데이터 초기화
        List<Player> players = new ArrayList<>();
        players.add(new Player("나 (Player)", true));
        players.add(new Player("플레이어 1", false));
        players.add(new Player("플레이어 2", false));
        players.add(new Player("플레이어 3", false));

        long accumulatedPot = 0;
        int roundCount = 1;
        boolean isRematchMode = false;
        long currentMultiplier = 1;

        // [UI-BIND] 밤일낮장 연출 시작 (UI 모달 또는 알림 팝업으로 표시)
        int firstPlayerIdx = determineFirstPlayerByNightAndDay(players);

        while (true) {
            checkAndProcessBankruptcies(players);
            currentMultiplier = updateMultiplierByEliminatedCount(players);

            List<Player> activeGamePlayers = getActiveGamePlayers(players);

            if (activeGamePlayers.size() <= 1) {
                // [UI-BIND] 최종 우승 결과 팝업 창 띄우기
                printFinalResult(activeGamePlayers);
                break;
            }

            while (players.get(firstPlayerIdx).isOut || !players.get(firstPlayerIdx).isRematchTarget) {
                firstPlayerIdx = (firstPlayerIdx + 1) % players.size();
            }

            long entryFee = BASE_ENTRY_FEE * currentMultiplier;
            long minBetUnit = BASE_BET_UNIT * currentMultiplier;

            // [UI-BIND] 라운드 헤더 UI 업데이트 (라운드 수, 현재 팟, 적용 배수)
            printRoundHeader(roundCount, isRematchMode, players.get(firstPlayerIdx).name,
                    activeGamePlayers.size(), currentMultiplier, entryFee, minBetUnit, accumulatedPot);

            // [DELETE/REMOVE] 콘솔용 텍스트 출력
            System.out.println("  [라운드 참가 및 입장료 현황]");

            for (Player p : players) {
                if (p.isOut)
                    continue;

                if (!isRematchMode) {
                    p.resetForNewRound();
                } else {
                    p.isFolded = false;
                    p.currentBet = 0;
                }

                if (!p.isRematchTarget)
                    continue;

                if (isRematchMode) {
                    // [UI-BIND] 재대결 상태 뱃지 및 잔액 갱신
                    System.out.printf("  • %-10s : [재대결 참가] 입장료 면제 (잔액: %,d원)\n", p.name, p.chips); // [DELETE/REMOVE]
                } else {
                    if (p.chips < entryFee) {
                        // [UI-BIND] 파산 알림 토스트/팝업
                        System.out.printf("  • [파산] %s 님이 입장료(%,d원) 부족으로 탈락! (잔액: %,d원)\n", p.name, entryFee, p.chips); // [DELETE/REMOVE]
                        p.isOut = true;
                    } else {
                        p.chips -= entryFee;
                        accumulatedPot += entryFee;
                        p.currentBet += entryFee;
                        // [UI-BIND] 플레이어 잔액 및 테이블 중앙 팟(Pot) 금액 애니메이션 갱신
                        System.out.printf("  • %-10s : 입장료 -%,d원 차감 (잔액: %,d원)\n", p.name, entryFee, p.chips); // [DELETE/REMOVE]
                    }
                }
                sleep(300); // [DELETE/REMOVE]
            }

            checkAndProcessBankruptcies(players);
            currentMultiplier = updateMultiplierByEliminatedCount(players);

            if (countActivePlayers(players) <= 1)
                continue;

            // [DELETE/REMOVE] 콘솔 구분선 및 메시지
            System.out.println("\n--------------------------------------------------");
            System.out.printf("  ▶ 라운드 시작 | 현재 총 누적 상금(팟): %,d원\n", accumulatedPot);
            System.out.println("--------------------------------------------------");
            sleep(DELAY_MS);

            // [MODEL/LOGIC] 덱 생성 및 카드 분배
            List<Card> deck = createDeck();
            Collections.shuffle(deck);

            int cardIdx = 0;
            for (int i = 0; i < players.size(); i++) {
                int pIdx = (firstPlayerIdx + i) % players.size();
                Player p = players.get(pIdx);
                if (!p.isOut && p.isRematchTarget) {
                    p.receiveCards(deck.get(cardIdx++), deck.get(cardIdx++));
                    // [UI-BIND] 카드 분배 애니메이션 (뒷면 상태로 카드 이동)
                }
            }

            // [MODEL/LOGIC] 특수 족보(구사, 암행어사 등) 재판정
            boolean reGameByGusa = false;
            String reGameReason = "";

            boolean has1318GwangDdang = hasRankInRange(players, Rank.GWANG_DDANG_1318, Rank.GWANG_DDANG_1318);
            boolean hasDdang1to9 = hasRankInRange(players, Rank.DDANG_9, Rank.DDANG_1);
            boolean hasTopRank = hasRankInRange(players, Rank.G38_KWANG, Rank.AL_LI);

            for (Player p : players) {
                if (p.isOut || !p.isRematchTarget)
                    continue;

                if (p.rank == Rank.AMHAENG_EOSA && !has1318GwangDdang) {
                    p.rank = Rank.KKUT_1;
                } else if (p.rank == Rank.DDANG_JABI && !hasDdang1to9) {
                    p.rank = Rank.MANG_TONG;
                } else if (p.rank == Rank.MUNG_GUSA || p.rank == Rank.GUSA) {
                    if (!hasTopRank) {
                        reGameByGusa = true;
                        reGameReason = p.rank.getName() + " 발동 (알리 이상의 패가 없어 파토)";
                    } else {
                        p.rank = Rank.KKUT_3;
                    }
                }
            }

            // ==================== Phase 1: 1차 베팅 ====================
            // [DELETE/REMOVE] 콘솔 전용 가이드 문구
            System.out.println("\n--------------------------------------------------");
            System.out.printf("  [ 1차 베팅 시작 | 기본 단위: %,d원 | 누적 상금: %,d원 ]\n", minBetUnit, accumulatedPot);
            System.out.println("--------------------------------------------------");
            sleep(DELAY_MS);

            // [UI-BIND] 베팅 UI 활성화 및 액션 입력 받기
            accumulatedPot = processBettingPhase(players, firstPlayerIdx, accumulatedPot, minBetUnit, 1);

            if (countAlivePlayers(players) <= 1) {
                firstPlayerIdx = handleSingleWinner(players, accumulatedPot);
                roundCount++;
                accumulatedPot = 0;
                isRematchMode = false;
                resetAllRematchTargets(players);

                checkAndProcessBankruptcies(players);
                currentMultiplier = updateMultiplierByEliminatedCount(players);
                continue;
            }

            // ==================== Phase 2: 1구패 공개 및 2차 베팅 ====================
            // [UI-BIND] 모든 플레이어의 첫 번째 카드 뒤집기 애니메이션 실행
            System.out.println("\n=================================================="); // [DELETE/REMOVE]
            System.out.printf("    [ 1구패 오픈 (첫 번째 패) | 누적 상금: %,d원 ]    \n", accumulatedPot); // [DELETE/REMOVE]
            System.out.println("=================================================="); // [DELETE/REMOVE]
            sleep(DELAY_MS); // [DELETE/REMOVE]

            for (int i = 0; i < players.size(); i++) {
                int pIdx = (firstPlayerIdx + i) % players.size();
                Player p = players.get(pIdx);
                if (p.isOut || !p.isRematchTarget)
                    continue;
                if (!p.isFolded) {
                    // [UI-BIND] 해당 플레이어 영역에 1구패 카드 이미지 뒤집어 오픈
                    System.out.printf("  %-12s : [%s, ?] (잔액: %,d원)\n", p.name, p.card1, p.chips); // [DELETE/REMOVE]
                } else {
                    System.out.printf("  %-12s : [ DIE ]\n", p.name); // [DELETE/REMOVE]
                }
                sleep(600); // [DELETE/REMOVE]
            }

            // [DELETE/REMOVE] 콘솔 전용 문구
            System.out.println("\n--------------------------------------------------");
            System.out.printf("  [ 2차 베팅 시작 | 기본 단위: %,d원 | 누적 상금: %,d원 ]\n", minBetUnit * 2, accumulatedPot);
            System.out.println("--------------------------------------------------");
            sleep(DELAY_MS);

            long maxBet = 0;
            for (Player p : players) {
                if (!p.isOut && p.isRematchTarget && !p.isFolded && p.currentBet > maxBet) {
                    maxBet = p.currentBet;
                }
            }
            // [UI-BIND] 2차 베팅 UI 호출
            accumulatedPot = processBettingPhase(players, firstPlayerIdx, accumulatedPot,
                    Math.max(maxBet, minBetUnit * 2), 2);

            if (countAlivePlayers(players) <= 1) {
                firstPlayerIdx = handleSingleWinner(players, accumulatedPot);
                roundCount++;
                accumulatedPot = 0;
                isRematchMode = false;
                resetAllRematchTargets(players);

                checkAndProcessBankruptcies(players);
                currentMultiplier = updateMultiplierByEliminatedCount(players);
                continue;
            }

            // ==================== Phase 3: 패 최종 공개 ====================
            // [UI-BIND] 남은 생존자들의 2구패까지 모두 오픈 & 최종 족보 UI 텍스트 출력
            System.out.println("\n=================================================="); // [DELETE/REMOVE]
            System.out.printf("     [ 패 최종 공개 및 결과 | 누적 상금: %,d원 ]     \n", accumulatedPot); // [DELETE/REMOVE]
            System.out.println("=================================================="); // [DELETE/REMOVE]
            sleep(DELAY_MS); // [DELETE/REMOVE]

            for (int i = 0; i < players.size(); i++) {
                int pIdx = (firstPlayerIdx + i) % players.size();
                Player p = players.get(pIdx);
                if (p.isOut || !p.isRematchTarget)
                    continue;
                if (p.isFolded) {
                    System.out.printf("  %-12s : [ DIE ]\n", p.name); // [DELETE/REMOVE]
                } else {
                    // [UI-BIND] 카드 2장 모두 오픈 + 족보이름(p.rank.getName()) 표시
                    System.out.printf("  %-12s : [%s, %s] -> %s (이번 라운드 투입: %,d원 | 잔액: %,d원)\n",
                            p.name, p.card1, p.card2, p.rank.getName(), p.currentBet, p.chips); // [DELETE/REMOVE]
                }
                sleep(800); // [DELETE/REMOVE]
            }

            if (reGameByGusa) {
                // [UI-BIND] 구사 파토 알림 팝업 오픈
                System.out.println("\n[구사 파토] " + reGameReason); // [DELETE/REMOVE]
                System.out.printf("★ 판돈 %,d원은 다음 라운드로 그대로 이월됩니다!\n", accumulatedPot); // [DELETE/REMOVE]
                roundCount++;
                isRematchMode = true;
                sleep(DELAY_MS); // [DELETE/REMOVE]
                continue;
            }

            // [MODEL/LOGIC] 승자 판정
            List<Player> alivePlayers = getAlivePlayers(players);
            Rank topRank = alivePlayers.get(0).rank;
            for (Player p : alivePlayers) {
                if (p.rank.ordinal() < topRank.ordinal()) {
                    topRank = p.rank;
                }
            }

            List<Player> winners = new ArrayList<>();
            for (Player p : alivePlayers) {
                if (p.rank == topRank) {
                    winners.add(p);
                }
            }

            if (winners.size() > 1) {
                // [UI-BIND] 무승부 연출 및 동점자 안내 팝업
                System.out.println("\n=================================================="); // [DELETE/REMOVE]
                System.out.printf("  [무승부 발생] 최고 족보: %s (%d명 동점)\n", topRank.getName(), winners.size()); // [DELETE/REMOVE]
                System.out.printf("  총 판돈 %,d원을 이월하고 동점자 재대결을 진행합니다!\n", accumulatedPot); // [DELETE/REMOVE]
                System.out.println("=================================================="); // [DELETE/REMOVE]

                for (Player p : players) {
                    p.isRematchTarget = winners.contains(p);
                }

                firstPlayerIdx = players.indexOf(winners.get(0));
                isRematchMode = true;
                roundCount++;
                sleep(DELAY_MS); // [DELETE/REMOVE]
            } else {
                Player finalWinner = winners.get(0);

                // [MODEL/LOGIC + UI-BIND] 팟 금액 이동 애니메이션 처리
                distributePotWithSidePot(players, alivePlayers, finalWinner, accumulatedPot);

                firstPlayerIdx = players.indexOf(finalWinner);
                accumulatedPot = 0;
                roundCount++;
                isRematchMode = false;
                resetAllRematchTargets(players);

                checkAndProcessBankruptcies(players);
                currentMultiplier = updateMultiplierByEliminatedCount(players);

                sleep(DELAY_MS); // [DELETE/REMOVE]
            }
        }
    }

    /**
     * [MODEL/LOGIC] 파산자 체크
     */
    private static void checkAndProcessBankruptcies(List<Player> players) {
        for (Player p : players) {
            if (!p.isOut && p.chips <= 0) {
                p.isOut = true;
                p.isRematchTarget = false;
                // [UI-BIND] 해당 플레이어 프로필 UI에 "파산/탈락" 뱃지 노출
                System.out.printf("\n  [알림] %s 님의 잔액이 0원이 되어 탈락 처리되었습니다.\n", p.name); // [DELETE/REMOVE]
            }
        }
    }

    /**
     * [MODEL/LOGIC] 배수 계산
     */
    private static long updateMultiplierByEliminatedCount(List<Player> players) {
        int eliminatedCount = INITIAL_PLAYERS_COUNT - countActivePlayers(players);
        if (eliminatedCount <= 0)
            return 1;
        return (long) Math.pow(3, eliminatedCount);
    }

    /**
     * [UI-BIND / LOGIC] 밤일낮장 로직
     * - UI 적용 시: 각 플레이어 카드가 1장씩 순차적으로 뒤집히는 그래픽 연출 적용
     */
    private static int determineFirstPlayerByNightAndDay(List<Player> players) {
        System.out.println("\n=================================================="); // [DELETE/REMOVE]
        System.out.println("       [밤일낮장] 선 결정을 위한 카드 뽑기        "); // [DELETE/REMOVE]
        System.out.println("=================================================="); // [DELETE/REMOVE]
        sleep(DELAY_MS); // [DELETE/REMOVE]

        int currentHour = LocalTime.now().getHour();
        boolean isNight = (currentHour >= 18 || currentHour < 6);

        // [UI-BIND] 화면 상단 타이틀에 "현재 시간: XX시 (밤일/낮장 규칙 적용)" 안내 표기
        System.out.printf("현재 시간: %02d시 -> [%s] 규칙 적용 (%s)\n\n", // [DELETE/REMOVE]
                currentHour, isNight ? "밤일" : "낮장",
                isNight ? "가장 낮은 월이 선" : "가장 높은 월이 선");
        sleep(DELAY_MS); // [DELETE/REMOVE]

        List<Player> candidates = getActiveGamePlayers(players);

        while (true) {
            List<Card> deck = createDeck();
            Collections.shuffle(deck);

            int targetMonth = isNight ? 11 : 0;
            List<Player> topCandidates = new ArrayList<>();

            System.out.println("  [카드 추첨 진행]"); // [DELETE/REMOVE]
            for (int i = 0; i < candidates.size(); i++) {
                Player p = candidates.get(i);
                Card picked = deck.get(i);
                // [UI-BIND] 각 후보의 카드 뽑기 연출
                System.out.printf("  • %-12s : [%s]\n", p.name, picked); // [DELETE/REMOVE]
                sleep(500); // [DELETE/REMOVE]

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
                Player winner = topCandidates.get(0);
                // [UI-BIND] "OO 님이 선으로 결정되었습니다!" 팝업 띄우기
                System.out.printf("\n★ [%s] 님이 최종 선(First)으로 결정되었습니다!\n", winner.name); // [DELETE/REMOVE]
                sleep(DELAY_MS); // [DELETE/REMOVE]
                return players.indexOf(winner);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < topCandidates.size(); i++) {
                sb.append(topCandidates.get(i).name);
                if (i < topCandidates.size() - 1)
                    sb.append(", ");
            }

            // [UI-BIND] "동률 발생! 동률자만 재추첨합니다" 이펙트 연출
            System.out.printf("\n[동률 발생] %s (%d명) 동률 발생!\n", sb.toString(), topCandidates.size()); // [DELETE/REMOVE]
            System.out.println("★ 해당 동률자들만 모아서 선 재추첨을 진행합니다...\n"); // [DELETE/REMOVE]
            sleep(DELAY_MS); // [DELETE/REMOVE]

            candidates = topCandidates;
        }
    }

    /**
     * [MODEL/LOGIC] 팟 분배 및 사이드 팟 로직
     */
    private static void distributePotWithSidePot(List<Player> allPlayers, List<Player> alivePlayers, Player winner,
            long totalPot) {
        // [UI-BIND] 라운드 승자 팝업 안내
        System.out.println("\n=================================================="); // [DELETE/REMOVE]
        System.out.printf("  ★ 라운드 최고 족보 승자: %s (%s)\n", winner.name, winner.rank.getName()); // [DELETE/REMOVE]

        long winnerMaxClaimPerPlayer = winner.currentBet;
        long winnerEarned = 0;

        for (Player p : allPlayers) {
            long contribution = Math.min(p.currentBet, winnerMaxClaimPerPlayer);
            winnerEarned += contribution;
        }

        winner.chips += winnerEarned;
        long remainingPot = totalPot - winnerEarned;

        // [UI-BIND] 승자에게 칩이 이동하는 그래픽 연출
        System.out.printf("  ★ 승자 %s 획득 상금: %,d원 (올인 지분 제한 적용)\n", winner.name, winnerEarned); // [DELETE/REMOVE]

        if (remainingPot > 0) {
            // [UI-BIND] 사이드 팟 연출
            System.out.printf("  ★ [사이드 팟 발생] 남은 상금 %,d원이 발생하여 차순위 플레이어에게 분배됩니다.\n", remainingPot); // [DELETE/REMOVE]

            List<Player> sidePotEligible = new ArrayList<>(alivePlayers);
            sidePotEligible.remove(winner);

            sidePotEligible.sort(Comparator.comparing((Player p) -> p.rank.ordinal())
                    .thenComparing((Player p) -> -p.currentBet));

            if (!sidePotEligible.isEmpty()) {
                Player secondWinner = sidePotEligible.get(0);
                secondWinner.chips += remainingPot;
                System.out.printf("  ★ 차순위 수령자: %s (%s) -> %,d원 획득!\n",
                        secondWinner.name, secondWinner.rank.getName(), remainingPot); // [DELETE/REMOVE]
            } else {
                winner.chips += remainingPot;
                System.out.printf("  ★ 수령 가능한 차순위자가 없어 남은 %,d원도 %s 님에게 합산됩니다.\n", remainingPot, winner.name); // [DELETE/REMOVE]
            }
        }

        // [UI-BIND] 정산 후 전체 플레이어의 UI 잔액 갱신
        System.out.println("--------------------------------------------------"); // [DELETE/REMOVE]
        System.out.println("  [최종 정산 후 잔액 목록]"); // [DELETE/REMOVE]
        for (Player p : allPlayers) {
            if (!p.isOut) {
                System.out.printf("  • %-10s : %,d원\n", p.name, p.chips); // [DELETE/REMOVE]
            }
        }
        System.out.println("=================================================="); // [DELETE/REMOVE]
    }

    /**
     * [UI-BIND] 베팅 페이즈 처리
     * UI 개발 시 이 부분은 무한 루프 방식 대신, **유저 버튼 클릭 이벤트를 받아 동작하는 상태 머신(State Machine)** 구조로
     * 개편하게 됩니다.
     */
    private static long processBettingPhase(List<Player> players, int startIdx, long pot, long startTargetAmount,
            int phase) {
        long targetCallAmount = startTargetAmount;
        int turnOffset = 0;
        int lastRaiserOffset = -1;

        while (true) {
            if (countAlivePlayers(players) <= 1)
                break;
            if (lastRaiserOffset != -1 && turnOffset == lastRaiserOffset)
                break;

            int currentIdx = (startIdx + turnOffset) % players.size();
            Player p = players.get(currentIdx);

            if (!p.isOut && p.isRematchTarget && !p.isFolded) {

                if (p.chips == 0) {
                    // [UI-BIND] "올인 상태 (자동 체크)" 말풍선 띄우기
                    System.out.printf("> %s: 올인 상태 (자동 체크/콜) [현재 누적 상금: %,d원]\n", p.name, pot); // [DELETE/REMOVE]
                    sleep(600); // [DELETE/REMOVE]
                    turnOffset = (turnOffset + 1) % players.size();
                    if (lastRaiserOffset == -1)
                        lastRaiserOffset = turnOffset;
                    continue;
                }

                long neededToCall = targetCallAmount - p.currentBet;

                if (p.isHuman) {
                    printRealtimeBalances(players); // [DELETE/REMOVE]

                    long doubleTarget = targetCallAmount * 2;
                    long doubleAddAmount = doubleTarget - p.currentBet;

                    // =========================================================================
                    // [UI-BIND] 버튼 제어 지점!
                    // 콘솔의 SCANNER 대신 UI의 버튼 4개 [콜], [다이], [더블], [올인]을 활성화하고
                    // 사용자 선택 결과(choice)를 리스너 이벤트로 전달받도록 개편합니다.
                    // =========================================================================
                    System.out.printf("[내 턴] 패: [%s, %s] (%s) | 내 잔액: %,d원\n", p.card1, p.card2, p.rank.getName(),
                            p.chips); // [DELETE/REMOVE]
                    System.out.printf("  ★ 현재 테이블 총 누적 상금: %,d원 | 콜 필요 금액: %,d원\n", pot, neededToCall); // [DELETE/REMOVE]
                    System.out.printf("  [선택] 1.콜(%,d원) | 2.다이 | 3.더블(추가 %,d원) | 4.올인(%,d원): ", // [DELETE/REMOVE]
                            Math.min(neededToCall, p.chips), doubleAddAmount, p.chips); // [DELETE/REMOVE]

                    // [DELETE/REMOVE] 콘솔 사용자 입력 수집부
                    int choice = 1;
                    try {
                        choice = Integer.parseInt(SCANNER.nextLine().trim());
                    } catch (NumberFormatException e) {
                        choice = 1;
                    }
                    // =========================================================================

                    if (choice == 2) {
                        p.isFolded = true;
                        // [UI-BIND] 내 캐릭터 말풍선: "다이"
                        System.out.printf("> 나: 다이 (기권) [현재 누적 상금: %,d원]\n", pot); // [DELETE/REMOVE]
                    } else if (choice == 3) {
                        if (p.chips <= doubleAddAmount) {
                            pot += executeAllIn(p, targetCallAmount, pot);
                            if (p.currentBet > targetCallAmount) {
                                targetCallAmount = p.currentBet;
                                lastRaiserOffset = turnOffset;
                            }
                        } else {
                            targetCallAmount = doubleTarget;
                            p.currentBet += doubleAddAmount;
                            p.chips -= doubleAddAmount;
                            pot += doubleAddAmount;
                            lastRaiserOffset = turnOffset;
                            // [UI-BIND] 내 캐릭터 말풍선: "더블!" + 베팅 칩 애니메이션
                            System.out.printf("> 나: 더블! (+%,d원 차감 -> 잔액: %,d원) [현재 누적 상금: %,d원]\n",
                                    doubleAddAmount, p.chips, pot); // [DELETE/REMOVE]
                        }
                    } else if (choice == 4) {
                        pot += executeAllIn(p, targetCallAmount, pot);
                        if (p.currentBet > targetCallAmount) {
                            targetCallAmount = p.currentBet;
                            lastRaiserOffset = turnOffset;
                        }
                    } else {
                        long payAmount = Math.min(neededToCall, p.chips);
                        p.currentBet += payAmount;
                        p.chips -= payAmount;
                        pot += payAmount;
                        if (lastRaiserOffset == -1)
                            lastRaiserOffset = turnOffset;
                        // [UI-BIND] 내 캐릭터 말풍선: "콜" + 베팅 칩 애니메이션
                        System.out.printf("> 나: 콜 (+%,d원 차감 -> 잔액: %,d원) [현재 누적 상금: %,d원]\n",
                                payAmount, p.chips, pot); // [DELETE/REMOVE]
                    }
                    sleep(DELAY_MS); // [DELETE/REMOVE]
                } else {
                    // [MODEL/AI] AI 베팅 의사결정
                    sleep(800); // [DELETE/REMOVE]
                    boolean aiAllIn = false;
                    boolean aiDouble = false;
                    boolean aiCall = false;

                    double rand = Math.random();
                    int rankOrder = p.rank.ordinal();

                    if (phase == 1) {
                        if (rankOrder <= Rank.AL_LI.ordinal()) {
                            if (rand < 0.15)
                                aiAllIn = true;
                            else if (rand < 0.85)
                                aiDouble = true;
                            else
                                aiCall = true;
                        } else if (rankOrder <= Rank.KKUT_7.ordinal()) {
                            if (rand < 0.03)
                                aiAllIn = true;
                            else if (rand < 0.50)
                                aiDouble = true;
                            else
                                aiCall = true;
                        } else {
                            if (rand < 0.30)
                                aiDouble = true;
                            else if (rand < 0.80)
                                aiCall = true;
                        }
                    } else {
                        if (rankOrder <= Rank.DDANG_1.ordinal()) {
                            if (rand < 0.25)
                                aiAllIn = true;
                            else if (rand < 0.90)
                                aiDouble = true;
                            else
                                aiCall = true;
                        } else if (rankOrder <= Rank.AL_LI.ordinal()) {
                            if (rand < 0.10)
                                aiAllIn = true;
                            else if (rand < 0.70)
                                aiDouble = true;
                            else
                                aiCall = true;
                        } else if (rankOrder <= Rank.KKUT_7.ordinal()) {
                            if (neededToCall > p.chips * 0.3) {
                                if (rand < 0.40)
                                    aiCall = true;
                            } else {
                                if (rand < 0.30)
                                    aiDouble = true;
                                else
                                    aiCall = true;
                            }
                        } else {
                            if (neededToCall == 0) {
                                if (rand < 0.20)
                                    aiDouble = true;
                                else
                                    aiCall = true;
                            } else if (rand < 0.10) {
                                aiDouble = true;
                            }
                        }
                    }

                    if (aiAllIn) {
                        pot += executeAllIn(p, targetCallAmount, pot);
                        if (p.currentBet > targetCallAmount) {
                            targetCallAmount = p.currentBet;
                            lastRaiserOffset = turnOffset;
                        }
                    } else if (aiDouble) {
                        long doubleTarget = targetCallAmount * 2;
                        long doubleAddAmount = doubleTarget - p.currentBet;

                        if (p.chips <= doubleAddAmount) {
                            pot += executeAllIn(p, targetCallAmount, pot);
                            if (p.currentBet > targetCallAmount) {
                                targetCallAmount = p.currentBet;
                                lastRaiserOffset = turnOffset;
                            }
                        } else {
                            targetCallAmount = doubleTarget;
                            p.currentBet += doubleAddAmount;
                            p.chips -= doubleAddAmount;
                            pot += doubleAddAmount;
                            lastRaiserOffset = turnOffset;
                            // [UI-BIND] 해당 AI 말풍선: "더블!" 표시
                            System.out.printf("> %s: 더블! (+%,d원 차감 -> 잔액: %,d원) [현재 누적 상금: %,d원]\n",
                                    p.name, doubleAddAmount, p.chips, pot); // [DELETE/REMOVE]
                        }
                    } else if (aiCall || neededToCall == 0) {
                        long payAmount = Math.min(neededToCall, p.chips);
                        p.currentBet += payAmount;
                        p.chips -= payAmount;
                        pot += payAmount;
                        if (lastRaiserOffset == -1)
                            lastRaiserOffset = turnOffset;
                        // [UI-BIND] 해당 AI 말풍선: "콜" 표시
                        System.out.printf("> %s: 콜 (+%,d원 차감 -> 잔액: %,d원) [현재 누적 상금: %,d원]\n",
                                p.name, payAmount, p.chips, pot); // [DELETE/REMOVE]
                    } else {
                        p.isFolded = true;
                        // [UI-BIND] 해당 AI 말풍선 및 카드 구역에 "다이" 표시
                        System.out.printf("> %s: 다이 (잔액: %,d원) [현재 누적 상금: %,d원]\n", p.name, p.chips, pot); // [DELETE/REMOVE]
                    }
                    sleep(DELAY_MS); // [DELETE/REMOVE]
                }
            }

            turnOffset = (turnOffset + 1) % players.size();
        }

        return pot;
    }

    /**
     * [MODEL/LOGIC] 올인 수행 메서드
     */
    private static long executeAllIn(Player p, long targetCallAmount, long currentPot) {
        long allInAmount = p.chips;
        p.currentBet += allInAmount;
        p.chips = 0;
        long newPot = currentPot + allInAmount;
        // [UI-BIND] 올인 연출 (사운드, 화려한 이펙트, 말풍선 "올인!")
        System.out.printf("> %s: 올인! (+%,d원 차감 -> 잔액: 0원) [현재 누적 상금: %,d원]\n",
                p.name, allInAmount, newPot); // [DELETE/REMOVE]
        return allInAmount;
    }

    // [DELETE/REMOVE] 콘솔 전용 잔액 모니터링 출력 함수
    private static void printRealtimeBalances(List<Player> players) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("[전체 참가자 실시간 상태 현황]");
        for (Player other : players) {
            if (!other.isOut && other.isRematchTarget) {
                if (!other.isFolded) {
                    System.out.printf("  • %-10s : 잔액 %,d원 (라운드 베팅액: %,d원)\n", other.name, other.chips,
                            other.currentBet);
                } else {
                    System.out.printf("  • %-10s : [ DIE ] (잔액 %,d원)\n", other.name, other.chips);
                }
            }
        }
        System.out.println("--------------------------------------------------");
    }

    // [MODEL/LOGIC] 유틸리티 메서드들
    private static boolean hasRankInRange(List<Player> players, Rank start, Rank end) {
        for (Player p : players) {
            if (p.isOut || !p.isRematchTarget)
                continue;
            if (p.rank.ordinal() >= start.ordinal() && p.rank.ordinal() <= end.ordinal()) {
                return true;
            }
        }
        return false;
    }

    private static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>(20);
        for (int month = 1; month <= 10; month++) {
            boolean isKwang = (month == 1 || month == 3 || month == 8);
            boolean isYeol = (month == 4 || month == 7 || month == 9);
            deck.add(new Card(month, isKwang, isYeol));
            deck.add(new Card(month, false, false));
        }
        return deck;
    }

    private static List<Player> getActiveGamePlayers(List<Player> players) {
        List<Player> active = new ArrayList<>();
        for (Player p : players) {
            if (!p.isOut)
                active.add(p);
        }
        return active;
    }

    private static int countActivePlayers(List<Player> players) {
        return getActiveGamePlayers(players).size();
    }

    private static int countAlivePlayers(List<Player> players) {
        return getAlivePlayers(players).size();
    }

    private static List<Player> getAlivePlayers(List<Player> players) {
        List<Player> alive = new ArrayList<>();
        for (Player p : players) {
            if (!p.isOut && p.isRematchTarget && !p.isFolded)
                alive.add(p);
        }
        return alive;
    }

    private static void resetAllRematchTargets(List<Player> players) {
        for (Player p : players) {
            p.isRematchTarget = true;
        }
    }

    private static int handleSingleWinner(List<Player> players, long pot) {
        Player winner = getAlivePlayers(players).get(0);
        winner.chips += pot;
        // [UI-BIND] 기권승 연출 및 칩 수령 애니메이션
        System.out.println("\n=================================================="); // [DELETE/REMOVE]
        System.out.printf("  ★ 라운드 승자: %s (상대 전원 기권)\n", winner.name); // [DELETE/REMOVE]
        System.out.printf("  ★ 획득 상금: %,d원 (최종 잔액: %,d원)\n", pot, winner.chips); // [DELETE/REMOVE]
        System.out.println("=================================================="); // [DELETE/REMOVE]
        sleep(DELAY_MS); // [DELETE/REMOVE]
        return players.indexOf(winner);
    }

    // [DELETE/REMOVE] 콘솔 라운드 타이틀 렌더링 함수
    private static void printRoundHeader(int round, boolean isRematch, String firstName, int activeCount,
            long currentMultiplier, long fee, long minBetUnit, long currentPot) {
        System.out.println("\n==================================================");
        if (isRematch) {
            System.out.printf("          [ ROUND %d - 비긴 자들의 재대결 ]         \n", round);
        } else {
            System.out.printf("                 [ ROUND %d ]                 \n", round);
        }
        System.out.printf("  [선]: %s | 생존: %d명 | 이월 및 현재 누적 상금: %,d원\n", firstName, activeCount, currentPot);

        if (!isRematch) {
            System.out.printf("  [현재 적용 배수]: %,d배 | [기본 입장료]: %,d원 | [기본 베팅 단위]: %,d원\n",
                    currentMultiplier, fee, minBetUnit);
        } else {
            System.out.println("  [규칙]: 무승부 재대결 라운드로 입장료가 면제됩니다.");
        }
        System.out.println("==================================================");
        sleep(DELAY_MS);
    }

    // [DELETE/REMOVE] 콘솔 최종 결과 출력 함수
    private static void printFinalResult(List<Player> activeGamePlayers) {
        System.out.println("\n==================================================");
        System.out.println("                [ 최종 게임 종료 ]");
        if (activeGamePlayers.size() == 1) {
            System.out.printf("  ★ 최후의 승자: %s (최종 잔액: %,d원)\n",
                    activeGamePlayers.get(0).name, activeGamePlayers.get(0).chips);
        } else {
            System.out.println("  모든 플레이어가 파산했습니다.");
        }
        System.out.println("==================================================");
    }
}