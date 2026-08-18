package poke;

import java.util.Random;

public class PokeMain {
    public static void main(String[] args) {
        
        String[] sname = {"피카츄", "파이리"};
        int[] nhp = {100, 80};
        String[] stype = {"전기", "불"};
        
        Pokemon p1 = new Pokemon();
        p1.name = sname[new Random().nextInt(2)];
        p1.hp = nhp[new Random().nextInt(2)];
        p1.type = stype[new Random().nextInt(2)];
        p1.info();

    }//main
}
