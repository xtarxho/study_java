package ex6_abstract;

public class UnitMain {
    public static void main(String[] args) {
        
        Terran t1 = new Terran("해병", 100);
        t1.decEnergy();
        System.out.println("t1 : " + t1.energy);

        Zerg z1 = new Zerg("드론", 50);
        z1.decEnergy();
        System.out.println("z1 : " + z1.energy);

        
    }//main
}
