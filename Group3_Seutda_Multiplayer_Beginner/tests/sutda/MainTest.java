package sutda;

import java.util.List;

/** 발표 전에 핵심 카드 규칙을 빠르게 확인하는 간단한 테스트입니다. */
public class MainTest {
    /**
     * 역할: 20장 덱 생성과 주요 족보 판정이 예상대로 동작하는지 순서대로 확인합니다.
     * 작성 이유: 별도 테스트 프레임워크 없이 발표 직전에 javac/java만으로 빠르게 핵심 규칙을 검증할 수 있게 만든 간단한 실행형 테스트입니다.
     */
    public static void main(String[] args) {
        List<Card> deck = Card.createDeck();
        check(deck.size() == 20, "카드가 20장 생성되는가");
        check(new Card(1).month == 1, "1번 카드가 1월인가");

        check("38광땡".equals(Card.evaluate(new Card(5), new Card(15)).rank.name),
                "38광땡을 인식하는가");
        check("장땡".equals(Card.evaluate(new Card(19), new Card(20)).rank.name),
                "장땡을 인식하는가");
        check("알리".equals(Card.evaluate(new Card(2), new Card(4)).rank.name),
                "알리를 인식하는가");
        check("13/18광땡".equals(Card.evaluate(new Card(1), new Card(5)).rank.name),
                "13광땡을 인식하는가");
        check(!"13/18광땡".equals(Card.evaluate(new Card(1), new Card(6)).rank.name),
                "3월 일반패를 13광땡으로 잘못 인식하지 않는가");
        check("땡잡이".equals(Card.evaluate(new Card(5), new Card(14)).rank.name),
                "3광+7열 땡잡이를 인식하는가");
        check("암행어사".equals(Card.evaluate(new Card(8), new Card(14)).rank.name),
                "4열+7열 암행어사를 인식하는가");
        check("멍텅구리 구사".equals(Card.evaluate(new Card(8), new Card(18)).rank.name),
                "4열+9열 멍텅구리 구사를 인식하는가");
        check("구사".equals(Card.evaluate(new Card(7), new Card(17)).rank.name),
                "일반 4월+9월 구사를 인식하는가");

        System.out.println("모든 기본 테스트를 통과했습니다.");
    }

    /**
     * 역할: 조건이 참이면 통과 메시지를 출력하고 거짓이면 AssertionError를 발생시킵니다.
     * 작성 이유: 각 테스트마다 동일한 if문과 출력 코드를 반복하지 않고 실패 지점을 바로 알 수 있도록 공통 검사 메서드로 만들었습니다.
     */
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError("실패: " + message);
        System.out.println("[통과] " + message);
    }
}
