package seotda_player;



public class test {
    public static void main(String[] args) {
        Deck deck = new Deck();
        HumanPlayer player = new HumanPlayer("성민");
    
        Card card1 = deck.draw();
        Card card2 = deck.draw();
    
        player.addCard(card1);
        player.addCard(card2);

        System.out.println(player.getName());
        System.out.println(player.getCard()[0]);
        System.out.println(player.getCard()[1]);
    }
}
