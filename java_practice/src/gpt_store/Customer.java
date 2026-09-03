package gpt_store;

public class Customer {

    //고객의 이름을 저장하는 변수
    private String name;

    //고객을 만들 때 이름을 전달 받는다
    public Customer(String name){
        this.name = name;
    }

    //고객의 이름을 가져오는 메서드
    public String getName(){
        return name;
    }

    //고객이 상품을 구매하는 메서드
    public void buy(Product product){

        //Product 클래스의 sell 메서드를 호출한다
        //상품을 판매하면서 재고가 1개씩 감소
        product.sell();
    }

}

