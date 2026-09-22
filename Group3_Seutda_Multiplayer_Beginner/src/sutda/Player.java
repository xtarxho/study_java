package sutda;

/** 플레이어 한 명의 상태를 저장합니다. */
public class Player {
    final String name;
    final boolean human;

    boolean folded;
    boolean out;
    boolean rematchTarget = true;
    Card card1;
    Card card2;
    long currentBet;
    long money;

    /** 이름, 사람/AI 여부, 시작 금액만 받아 플레이어를 만듭니다. */
    public Player(String name, boolean human, long money) {
        this.name = name;
        this.human = human;
        this.money = money;
    }

    /** 새 라운드의 카드와 베팅 상태를 한 번에 초기화합니다. */
    void resetRound(Card card1, Card card2) {
        this.card1 = card1;
        this.card2 = card2;
        folded = false;
        currentBet = 0;
    }
}
