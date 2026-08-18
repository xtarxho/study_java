package poke;

public class Pokemon {

    String name;
    int hp;
    String type;

    public void info() {
        System.out.println("야생의 " + name + "이(가) 튀어나왔다");
        System.out.printf("hp : %d / type : %s\n", hp, type);
        System.out.println("---------------------------");
    }

}
