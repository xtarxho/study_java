package ex04_work;

public class Vending {
    
    private Can[] cans = new Can[5];
    private int money;

    //자판기에서 관리할 음료수를 준비
    // public void init(){

    //     for( int i = 0; i < cans.length; i++ ){
    //         cans[i] = new Can();
    //     }//for

    //     cans[0].setName("환타");
    //     cans[0].setPrice(1000);

    //     cans[1].setName("사이다");
    //     cans[1].setPrice(1200);

    //     cans[2].setName("핫식스");
    //     cans[2].setPrice(1300);

    //     cans[3].setName("콜라");
    //     cans[3].setPrice(1400);
        
    //     cans[4].setName("오렌지");
    //     cans[4].setPrice(1500);

    // }//init

    //생성자로 다시 만들어보기
    public Vending(){
        cans[0] = new Can("환타", 1000);
        cans[1] = new Can("콜라", 1100);
        cans[2] = new Can("사이다", 1200);
        cans[3] = new Can("몬스터", 1300);
        cans[4] = new Can("맥콜", 1400);
    }





    //사용자의 돈을 받는다
    //이 돈으로 먹을 수 있는 음료 목록만 보여주기
    public void showDirnk(int money){

        for( int i = 0; i < cans.length; i++ ){

            if ( cans[i].getPrice() <= money ) {
                System.out.printf( "%s - %d원\n",cans[i].getName(),cans[i].getPrice() );
            }

        }

        this.money =money;

        System.out.print(">> ");

    }//showDrink()

    //사용자가 음료수를 선택하면
    //해당 음료수를 제공하고 잔돈을 돌려준다
    public void dispense(String name){

        for( int i = 0; i <cans.length; i++ ){

            if (cans[i].getName().equals(name)) {
                System.out.println(name + "을(를) 선택함");
                money -= cans[i].getPrice(); //잔돈 계산

                System.out.println("잔액 : " + money);
            }

        }//for

    }//dispense()


}
