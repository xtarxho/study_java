package ex05_overload;

public class OverMain {
    public static void main(String[] args) {

        OverSub os = new OverSub();
        os.result();
        os.result(100);
        os.result('A');
        os.result("a");
        os.result(1,"a");
        os.result("r", 100);

        
    }//main
}
