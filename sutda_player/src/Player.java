package sutda_player;

abstract public class Player { //휴먼과 ai의 부모 클래스
    
    private String name; //플레이어 이름 저장 변수
    private Card[] card = new Card[2]; //플레이어가 가지고 있는 카드 2장 저장 배열
    private int money; // 플레이어가 가지고 있는 돈 저장 변수
    private boolean fold; // 다이했는지 여부 저장

    public Player(String name, int money){ //플레이어 객체가 생성될 때 이름과 돈을 전달받는 생성자
        this.name = name; //전달받은 name을 현재 객체 name에 저장
        this.money = money;
        this.fold = false;
    }

    public String getName(){ //플레이어 이름을 가져오는 메서드
        return name; //현재 플레이어의 이름을 돌려준다
    }

    public Card[] getCard(){ //플레이어가 가지고 있는 카드 배열을 가져오는 메서드
        return card; //현재 플레이어의 카드를 돌려준다
    }

    public int getMoney(){ //플레이어의 돈을 가져오는 메서드
        return money;
    }

    public void setMoney(int money){ //플레이어의 돈을 설정하는 메서드
        this.money = money;
    }

    public boolean isFold(){ //플레이어가 다이했는지 확인하는 메서드
        return fold;
    }

    public void fold(){ //플레이어를 다이 상태로 변경하는 메서드
        this.fold = true; 
    }

    int cardCount = 0; //현재까지 받은 카드의 개수를 저장하는 변수

    //Card 객체 한 장을 전달받아 플레이어의 카드 배열에 저장하는 메서드
    public void addCard(Card card){ 

        //카드 배열이 가득 찬 경우에는 카드를 추가하지 않음
        if (cardCount >= this.card.length) { 
            return;
        }

        this.card[cardCount] = card; //cardcount가 가리키는 위치에 card 객체 저장
        cardCount++; //다음 카드 저장을 위해 1증가
    }

    abstract public void play();

}
