package sutda_player;



public class test {
    public static void main(String[] args) {
        Deck deck = new Deck();
        HumanPlayer player = new HumanPlayer("성민", 10000);
        AiPlayer player1 = new AiPlayer("로봇", 10000);

        deck.shuffle();
        Card card1 = deck.draw();
        Card card2 = deck.draw();
        Card card3 = deck.draw();
        Card card4 = deck.draw();

        player.addCard(card1);
        player.addCard(card2);
    
        
        player1.addCard(card3);
        player1.addCard(card4);

        System.out.println(player.getName());
        System.out.println(player.getCard()[0]);
        System.out.println(player.getCard()[1]);
        System.out.println(player.getMoney());
        System.out.println(player.isFold());

        System.out.println(player1.getName());
        System.out.println(player1.getCard()[0]);
        System.out.println(player1.getCard()[1]);
        System.out.println(player1.getMoney());
        System.out.println(player1.isFold());
    }
}
