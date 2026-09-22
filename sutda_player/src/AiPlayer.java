package sutda_player;

public class AiPlayer extends Player {

    public AiPlayer(String name, int money) {
        super(name, money);

    }

    @Override
    public void play() {
        // AI가 가지고 있는 카드 2장을 가져온다.
        Card[] cards = getCard();

        // 첫 번째 카드의 번호를 가져온다.
        int card1 = cards[0].getNumber();

        // 두 번째 카드의 번호를 가져온다.
        int card2 = cards[1].getNumber();

        // AI가 어떤 행동을 할지 결정하기 위한 랜덤 숫자
        // 0 ~ 99 사이의 숫자가 나온다.
        int random = (int) (Math.random() * 100);

        // 두 카드의 숫자가 같으면 땡

        if (card1 == card2) {

            // 38광땡
            if ((card1 == 3 && card2 == 8) ||
                    (card1 == 8 && card2 == 3)) {

                // 98% 레이즈
                if (random < 98) {
                    System.out.println(getName() + " : 레이즈");

                    // 2% 콜
                } else {
                    System.out.println(getName() + " : 콜");
                }
            }

            // 18광땡
            else if ((card1 == 1 && card2 == 8) ||
                    (card1 == 8 && card2 == 1)) {

                // 95% 레이즈
                if (random < 95) {
                    System.out.println(getName() + " : 레이즈");

                    // 5% 콜
                } else {
                    System.out.println(getName() + " : 콜");
                }
            }

            // 13광땡
            else if ((card1 == 1 && card2 == 3) ||
                    (card1 == 3 && card2 == 1)) {

                // 92% 레이즈
                if (random < 92) {
                    System.out.println(getName() + " : 레이즈");

                    // 8% 콜
                } else {
                    System.out.println(getName() + " : 콜");
                }
            }

            // 장땡 확인

            // 두 카드가 같으면 땡이다.
            else if (card1 == card2) {

                // 같은 숫자이므로 몇 땡인지 확인한다.
                int ddaeng = card1;

                // 10땡 = 장땡
                if (ddaeng == 10) {

                    // 90% 레이즈
                    if (random < 90) {
                        System.out.println(getName() + " : 레이즈");

                        // 10% 콜
                    } else {
                        System.out.println(getName() + " : 콜");
                    }
                }

                // 일반 땡

                // 9땡
                else if (ddaeng == 9) {

                    if (random < 88) {
                        System.out.println(getName() + " : 레이즈");
                    } else if (random < 100) {
                        System.out.println(getName() + " : 콜");
                    }
                }

                // 8땡
                else if (ddaeng == 8) {

                    if (random < 82) {
                        System.out.println(getName() + " : 레이즈");
                    } else if (random < 99) {
                        System.out.println(getName() + " : 콜");
                    } else {
                        System.out.println(getName() + " : 다이");
                        fold();
                    }
                }

                // 7땡
                else if (ddaeng == 7) {

                    if (random < 76) {
                        System.out.println(getName() + " : 레이즈");
                    } else if (random < 97) {
                        System.out.println(getName() + " : 콜");
                    } else {
                        System.out.println(getName() + " : 다이");
                        fold();
                    }
                }

                // 6땡
                else if (ddaeng == 6) {

                    if (random < 68) {
                        System.out.println(getName() + " : 레이즈");
                    } else if (random < 94) {
                        System.out.println(getName() + " : 콜");
                    } else {
                        System.out.println(getName() + " : 다이");
                        fold();
                    }
                }

                // 5땡
                else if (ddaeng == 5) {

                    if (random < 58) {
                        System.out.println(getName() + " : 레이즈");
                    } else if (random < 90) {
                        System.out.println(getName() + " : 콜");
                    } else {
                        System.out.println(getName() + " : 다이");
                        fold();
                    }
                }

                // 4땡
                else if (ddaeng == 4) {

                    if (random < 48) {
                        System.out.println(getName() + " : 레이즈");
                    } else if (random < 85) {
                        System.out.println(getName() + " : 콜");
                    } else {
                        System.out.println(getName() + " : 다이");
                        fold();
                    }
                }

                // 3땡
                else if (ddaeng == 3) {

                    if (random < 38) {
                        System.out.println(getName() + " : 레이즈");
                    } else if (random < 80) {
                        System.out.println(getName() + " : 콜");
                    } else {
                        System.out.println(getName() + " : 다이");
                        fold();
                    }
                }

                // 2땡
                else if (ddaeng == 2) {

                    if (random < 28) {
                        System.out.println(getName() + " : 레이즈");
                    } else if (random < 70) {
                        System.out.println(getName() + " : 콜");
                    } else {
                        System.out.println(getName() + " : 다이");
                        fold();
                    }
                }

                // 1땡
                else {

                    if (random < 18) {
                        System.out.println(getName() + " : 레이즈");
                    } else if (random < 60) {
                        System.out.println(getName() + " : 콜");
                    } else {
                        System.out.println(getName() + " : 다이");
                        fold();
                    }
                }
            }

            // 땡이 아니면 끗을 계산한다.

        } else {

            // 두 카드의 합에서 일의 자리만 가져온다.
            // 예) 7 + 8 = 15 → 5끗
            // 예) 8 + 9 = 17 → 7끗
            // 예) 9 + 9 = 18 → 8끗
            int score = (card1 + card2) % 10;

            // 9끗

            if (score == 9) {

                // 9끗은 강한 패이므로 레이즈를 많이 한다.
                if (random < 65) {

                    System.out.println(getName() + " : 레이즈");

                    // 65 ~ 94 → 콜
                } else if (random < 95) {

                    System.out.println(getName() + " : 콜");

                    // 95 ~ 99 → 다이
                } else {

                    System.out.println(getName() + " : 다이");
                    fold();
                }

                // 8끗

            } else if (score == 8) {

                if (random < 50) {

                    System.out.println(getName() + " : 레이즈");

                } else if (random < 90) {

                    System.out.println(getName() + " : 콜");

                } else {

                    System.out.println(getName() + " : 다이");
                    fold();
                }

                // 7끗

            } else if (score == 7) {

                if (random < 35) {

                    System.out.println(getName() + " : 레이즈");

                } else if (random < 80) {

                    System.out.println(getName() + " : 콜");

                } else {

                    System.out.println(getName() + " : 다이");
                    fold();
                }

                // 6끗

            } else if (score == 6) {

                if (random < 25) {

                    System.out.println(getName() + " : 레이즈");

                } else if (random < 70) {

                    System.out.println(getName() + " : 콜");

                } else {

                    System.out.println(getName() + " : 다이");
                    fold();
                }

                // 5끗

            } else if (score == 5) {

                if (random < 15) {

                    System.out.println(getName() + " : 레이즈");

                } else if (random < 60) {

                    System.out.println(getName() + " : 콜");

                } else {

                    System.out.println(getName() + " : 다이");
                    fold();
                }

                // 4끗

            } else if (score == 4) {

                if (random < 10) {

                    System.out.println(getName() + " : 레이즈");

                } else if (random < 45) {

                    System.out.println(getName() + " : 콜");

                } else {

                    System.out.println(getName() + " : 다이");
                    fold();
                }

                // 3끗

            } else if (score == 3) {

                if (random < 7) {

                    System.out.println(getName() + " : 레이즈");

                } else if (random < 35) {

                    System.out.println(getName() + " : 콜");

                } else {

                    System.out.println(getName() + " : 다이");
                    fold();
                }

                // 2끗

            } else if (score == 2) {

                if (random < 5) {

                    System.out.println(getName() + " : 레이즈");

                } else if (random < 30) {

                    System.out.println(getName() + " : 콜");

                } else {

                    System.out.println(getName() + " : 다이");
                    fold();
                }

                // 1끗

            } else if (score == 1) {

                if (random < 3) {

                    System.out.println(getName() + " : 레이즈");

                } else if (random < 20) {

                    System.out.println(getName() + " : 콜");

                } else {

                    System.out.println(getName() + " : 다이");
                    fold();
                }

                // 망통 (0끗)

            } else {

                // 망통은 가장 약한 패이므로
                // 대부분 다이를 선택하도록 한다.
                if (random < 1) {

                    System.out.println(getName() + " : 레이즈");

                } else if (random < 10) {

                    System.out.println(getName() + " : 콜");

                } else {

                    System.out.println(getName() + " : 다이");
                    fold();
                }
            }
        }
    }
}
