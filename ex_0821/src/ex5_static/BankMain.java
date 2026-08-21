package ex5_static;

public class BankMain {
    public static void main(String[] args) {
        
        Bank b1 = new Bank("신촌", "02-111-1111");
        Bank b2 = new Bank("강남", "02-222-2222");
        Bank b3 = new Bank("역삼", "02-333-3333");
        
        //static은 클래스명. 형식으로 접근 가능하다
        Bank.interest = 0.1f;
        
        b1.info();
        b2.info();
        b3.info();


    }//main
}
