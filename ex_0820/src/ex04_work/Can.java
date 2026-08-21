package ex04_work;

public class Can {
    
    // private String name;
    // private int price;
    
    // public String getName() {
    //     return name;
    // }
    // public void setName(String name) {
    //     this.name = name;
    // }
    // public int getPrice() {
    //     return price;
    // }
    // public void setPrice(int price) {
    //     this.price = price;
    // }


    //생성자로 다시 만들어보기
    private String name;
    private int price;
    
    public Can(String name, int price){
        this.name = name;
        this.price = price;
        

    }

    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }
    

    


}
