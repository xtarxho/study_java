package computer;

public class ComMain {
    public static void main(String[] args) {
        
        Computer c1 = new Computer();
        c1.ssd = 1024;
        c1.cpu = 3.5f;
        c1.color = "black";
        //private변수 brand로는 접근이 불가
        //c1.brand = "apple";
        c1.info();
        // System.out.println("브랜드 : " + c1.brand);
        // System.out.println("ssd : " + c1.ssd);
        // System.out.println("ram : " + c1.ram);
        // System.out.println("cpu : " + c1.cpu);
        // System.out.println("color : " + c1.color);

       

        Computer c2 = new Computer();
        c2.info();
        // System.out.println("브랜드 : " + c2.brand);
        // System.out.println("ssd : " + c2.ssd);
        // System.out.println("ram : " + c2.ram);
        // System.out.println("cpu : " + c2.cpu);
        // System.out.println("색상 : " + c2.color);


    }//main
}
