package ex6_abstract;

public class Protoss extends Unit {
    
    public Protoss(String name, int energy){
        super.name = name;
        super.energy = energy;
        
    }

    @Override
    public void decEnergy() {
        super.energy --;
    }
}
