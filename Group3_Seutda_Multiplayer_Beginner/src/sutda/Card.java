package sutda;

import java.util.ArrayList;
import java.util.List;

/** 카드 정보와 섯다 족보 판정을 담당합니다. */
public class Card {
    final int number;
    final int month;
    final boolean gwang;
    final boolean yeol;

    public Card(int number) {
        if (number < 1 || number > 20) {
            throw new IllegalArgumentException("카드 숫자는 1부터 20 사이여야 합니다: " + number);
        }
        this.number = number;
        month = ((number - 1) / 2) + 1;
        gwang = number == 1 || number == 5 || number == 15;
        yeol = number % 2 == 0 && !gwang;
    }

    /** 1~20번 카드 한 벌을 만듭니다. */
    static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>(20);
        for (int i = 1; i <= 20; i++)
            deck.add(new Card(i));
        return deck;
    }

    /** 카드 두 장의 족보를 계산합니다. */
    static Hand evaluate(Card a, Card b) {
        int m1 = a.month;
        int m2 = b.month;

        if (a.gwang && b.gwang && matches(m1, m2, 3, 8))
            return new Hand(Rank.G38_KWANG);
        if (a.gwang && b.gwang && (matches(m1, m2, 1, 3) || matches(m1, m2, 1, 8))) {
            return new Hand(Rank.GWANG_DDANG_1318);
        }

        if (m1 == m2)
            return new Hand(Rank.values()[Rank.DDANG_10.ordinal() + (10 - m1)]);

        if (matches(m1, m2, 1, 2))
            return new Hand(Rank.AL_LI);
        if (matches(m1, m2, 1, 4))
            return new Hand(Rank.DOK_SA);
        if (matches(m1, m2, 1, 9))
            return new Hand(Rank.GU_BING);
        if (matches(m1, m2, 1, 10))
            return new Hand(Rank.JANG_BING);
        if (matches(m1, m2, 4, 10))
            return new Hand(Rank.JANG_SA);
        if (matches(m1, m2, 4, 6))
            return new Hand(Rank.SE_RYUK);

        if ((m1 == 4 && a.yeol && m2 == 7 && b.yeol) || (m1 == 7 && a.yeol && m2 == 4 && b.yeol)) {
            return new Hand(Rank.AMHAENG_EOSA);
        }
        if ((m1 == 3 && a.gwang && m2 == 7 && b.yeol) || (m1 == 7 && a.yeol && m2 == 3 && b.gwang)) {
            return new Hand(Rank.DDANG_JABI);
        }
        if ((m1 == 4 && a.yeol && m2 == 9 && b.yeol) || (m1 == 9 && a.yeol && m2 == 4 && b.yeol)) {
            return new Hand(Rank.MUNG_GUSA);
        }
        if (matches(m1, m2, 4, 9))
            return new Hand(Rank.GUSA);

        int kkut = (m1 + m2) % 10;
        return new Hand(kkut == 0 ? Rank.MANG_TONG : Rank.values()[Rank.KKUT_9.ordinal() + (9 - kkut)]);
    }

    private static boolean matches(int a, int b, int x, int y) {
        return (a == x && b == y) || (a == y && b == x);
    }

    static class Hand {
        final Rank rank;

        Hand(Rank rank) {
            this.rank = rank;
        }
    }

    enum Rank {
        G38_KWANG("38광땡", 300),
        GWANG_DDANG_1318("13/18광땡", 280),
        DDANG_10("장땡", 200), DDANG_9("9땡", 190), DDANG_8("8땡", 180),
        DDANG_7("7땡", 170), DDANG_6("6땡", 160), DDANG_5("5땡", 150),
        DDANG_4("4땡", 140), DDANG_3("3땡", 130), DDANG_2("2땡", 120), DDANG_1("1땡", 110),
        AL_LI("알리", 90), DOK_SA("독사", 80), GU_BING("구삥", 70),
        JANG_BING("장삥", 60), JANG_SA("장사", 50), SE_RYUK("세륙", 40),
        KKUT_9("9끗", 29), KKUT_8("8끗", 28), KKUT_7("7끗", 27),
        KKUT_6("6끗", 26), KKUT_5("5끗", 25), KKUT_4("4끗", 24),
        KKUT_3("3끗", 23), KKUT_2("2끗", 22), KKUT_1("1끗", 21),
        MANG_TONG("망통", 0),
        AMHAENG_EOSA("암행어사", 21), DDANG_JABI("땡잡이", 0),
        MUNG_GUSA("멍텅구리 구사", 23), GUSA("구사", 23);

        final String name;
        final int score;

        Rank(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    @Override
    public String toString() {
        return month + (gwang ? "광" : "");
    }
}
