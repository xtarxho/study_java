package ex4_hangman;

import java.util.Random;

public class HangmanMain {
    public static void main(String[] args) {
        
        String[] word = {"apple", "game"};
        int rnd = new Random().nextInt(word.length);

        PlayGame pg = new PlayGame();
        pg.play(word[rnd]);

    }//main
}
