package seotda_player;

abstract public class Player { //휴먼과 ai의 부모 클래스
    
    String name; //플레이어 이름 저장 변수
    Card[] card = new Card[2]; //플레이어가 가지고 있는 카드 2장 저장 배열

    public Player(String name){ //플레이어 객체가 생성될 때 이름 전달받는 생성자
        this.name = name; //전달받은 name을 현재 객체 name에 저장
    }

    public String getName(){ //플레이어 이름을 가져오는 메서드
        return name; //현재 플레이어의 이름을 돌려준다
    }

    public Card[] getCard(){ //플레이어가 가지고 있는 카드 배열을 가져오는 메서드
        return card; //현재 플레이어의 카드를 돌려준다
    }

    int cardCount = 0; //현재까지 받은 카드의 개수를 저장하는 변수

    //Card 객체 한 장을 전달받아 플레이어의 카드 배열에 저장하는 메서드
    public void addCard(Card card){ 
        this.card[cardCount] = card; //cardcount가 가리키는 위치에 card 객체 저장
        cardCount++; //다음 카드 저장을 위해 1증가
    }

    abstract public void play();

}
