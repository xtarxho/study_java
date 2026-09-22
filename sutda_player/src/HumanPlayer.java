package sutda_player;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, int money) { // humanPlayer 객체를 생성할 때 이름을 전달받는다
        super(name, money); // 부모 클래스 Player의 생성자를 호출해서 이름을 전달받는다

    }

    @Override
    public void play() { // Player에서 정의한 play() 메서드를 구현한다
        // 사람의 행동은 UI에서 결정한다
        System.out.println(getName() + "의 차례입니다.");
    }

    // UI에서 콜 버튼을 눌렀을 때 호출한다
    public void call() {

        System.out.println(getName() + " : 콜");

    }

    // UI에서 레이즈 버튼을 눌렀을 때 호출한다
    public void raise() {

        System.out.println(getName() + " : 레이즈");

    }

    // UI에서 다이 버튼을 눌렀을 때 호출한다
    public void die() {

        System.out.println(getName() + " : 다이");

        // 플레이어의 다이 상태를 true로 변경한다
        fold();

    }

}
