package sutda_player;

// 화투 카드 클래스
// 각 카드는 숫자(1~20)와 광 여부(특수 카드: 1, 3, 8)를 가진다.
public class Card {
    private int number; // 카드 숫자 (1~20)
    private int month; // 섯다 월 (1 ~ 10)
    private boolean gwang; // 광 여부 (true면 광, false면 일반)

    // 생성자: 카드 숫자를 입력하면 자동으로 광 여부를 판별한다.
    // 예: 1 → 1광, 5 → 3광, 15 → 8광, 나머지는 일반 카드
    public Card(int number) {

        try {
            if (number < 1 || number > 20) {
                throw new IllegalArgumentException("카드 숫자를 벗어남(1~20)");// 자바 기본규칙이 아니므로 따로 throw 붙임
            } // if
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("카드 숫자를 벗어남(1~20)");
            return;
        } // try - catch

        this.number = number;
        this.month = ((number - 1) / 2) + 1;// 1~ 20의 번호를 1 ~ 10월로 변환, 2개가 하나의 월이므로 2로 나누어지게 처리

        this.gwang = (number == 1 || number == 5 || number == 15);
    }// Card

    // 카드 숫자를 반환하는 메서드
    public int getNumber() {
        return number;
    }

    // 카드 월을 반환하는 메서드
    public int getMonth() {
        return month;
    }

    // 카드가 광인지 여부를 반환하는 메서드
    public boolean isGwang() {
        return gwang;
    }

    // 카드 정보를 문자열로 표현하는 메서드
    // 예: "1광", "3광", "8광", "5"
    @Override
    public String toString() {
        return month + (gwang ? "광" : "");
        // if (gwang) {
        // return month + "광";
        // } else {
        // return String.valueOf(month);
        // }
    }
}