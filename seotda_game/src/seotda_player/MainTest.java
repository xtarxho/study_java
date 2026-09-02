package seotda_player;

public class MainTest {


    public static void main(String[] args) {
        Deck deck = new Deck();
        
        System.out.println("--- 덱 생성 완료 (총 카드 수: " + deck.size() + "장) ---");
        
        // 덱 섞기 테스트
        deck.shuffle();
        System.out.println("--- 덱 섞기 완료 ---");
        
        // 카드 한 장씩 뽑아보기 테스트
        System.out.println("--- 카드 뽑기 시작 ---");
        while (deck.size() > 0) {
            Card card = deck.draw();
            System.out.println("뽑은 카드: " + card + " (고유번호: " + card.getNumber() + ", 월: " + card.getMonth() + "월, 광: " + card.isGwang() + ")");
        }
        
        System.out.println("남은 카드 수: " + deck.size());
    }
}

