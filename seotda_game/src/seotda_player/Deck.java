package seotda_player;

import java.util.*;

// 덱(카드 묶음)을 관리하는 클래스
// 섯다 게임에서는 1~10 숫자 카드가 두 벌 있어 총 20장을 사용한다.
public class Deck {
    private List<Card> cards; // 덱에 들어있는 카드들을 저장하는 리스트

    // 생성자: 덱을 초기화
    // initDeck()을 호출하여 20장의 카드를 생성
    public Deck() {
        cards = new ArrayList<>();
        initDeck();
    }

    // 덱 초기화 메서드
    // 1~20 숫자 카드를 만들어서 총 20장을 덱에 추가
    private void initDeck() {
        for (int i = 1; i <= 20; i++) {
            cards.add(new Card(i)); // 첫 번째 카드
        } // for
    }// initDeck

    // 덱을 섞는 메서드

    public void shuffle() {
        Random random = new Random();

        for (int i = cards.size() - 1; i > 0; i--) {

            // 0부터 i 사이의 무작위 index 생성
            int j = random.nextInt(i + 1);

            // i번째 카드와 j번째 카드의 위치를 서로 바꿈 = 무작위로 덱이 섞임
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);

        } // for

    }// shuffle

    // 카드 한 장을 뽑는 메서드
    // 덱의 맨 앞에서 카드를 제거하고 반환
    // 덱이 비어 있으면 null을 반환
    public Card draw() {
        if (cards.isEmpty())
            return null;
        return cards.remove(0);
    }

    // 덱에 남아 있는 카드 수를 반환하는 메서드
    public int size() {
        return cards.size();
    }
}// class
 // public class Deck {
 // private Card[] cards = new Card[20]; // 20장의 카드를 담을 배열

// public Deck() {
// // 반복문으로 1부터 20까지 카드를 생성해서 배열에 넣기
// for (int i = 0; i < 20; i++) {
// cards[i] = new Card(i + 1); // 1번부터 20번까지 생성
// }
// }

// // 특정 번째 카드를 가져오는 메서드
// public Card getCard(int index) {
// return cards[index];
// }
// }