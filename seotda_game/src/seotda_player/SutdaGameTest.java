package seotda_player;

public class SutdaGameTest {
    public static void main(String[] args) {
        Deck deck = new Deck();

        // 1. 덱 섞기
        deck.shuffle();
        System.out.println("=== 섯다 덱 섞기 완료 (남은 카드: " + deck.size() + "장) ===");

        int playerCount = 4;
        int cardsPerPlayer = 2;

        // 2. 4명에게 각각 2장씩 카드 분배
        for (int i = 1; i <= playerCount; i++) {
            System.out.print("플레이어 " + i + " 손패: ");
            for (int j = 0; j < cardsPerPlayer; j++) {
                Card card = deck.draw();
                System.out.print("[" + card + " (고유번호: " + card.getNumber() + ", " + card.getMonth() + "월"
                        + (card.isGwang() ? ", 광" : "") + ")]");
                if (j < cardsPerPlayer - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        // 3. 분배 후 남은 덱 확인
        System.out.println("=== 분배 완료 후 남은 카드 수: " + deck.size() + "장 ===");
    }
}
