package ex6_abstract;

public class Zerg extends Unit {
    
    public Zerg(String name, int energy){
        super.name = name;
        super.energy = energy;
    }

    @Override
    public void decEnergy() {
        super.energy -= 10;
    }
}
