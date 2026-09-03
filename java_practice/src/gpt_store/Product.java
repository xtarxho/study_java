package gpt_store;

public class Product { // 상품 자체를 담당하는 클래스
    
    // 상품 정보
    private String name;
    private int price;
    private int stock; //재고품

    // 생성자 -> 상품을 만들 때 이름, 가격, 재고를 전달받는다
    public Product(String name, int price, int stock){
        this.name = name; //name을 this(현재클래스).name에게 저장 
        this.price = price; //name과 같음
        this.stock = stock; //같음

    }
    //main에서 가져온 데이터를 저장한다
    //상품 이름을 가져오는 메서드
    public String getName(){
        return name;
    }

    //상품 가격을 가져오는 메서드
    public int getPrice(){
        return price;
    }

    //상품 재고를 가져오는 메서드
    public int getStock(){
        return stock;
    }

    //상품을 구매하는 메서드
    public void sell(){

        //재고가 0보다 크면 상품을 판매할 수 있다
        if (stock > 0) {
            //재고 1개 감소
            stock--;
            System.out.println("상품 구매 완료");
        }else{

            System.out.println("재고가 없음");
        }

    }

}
