package gpt_store;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Product product = new Product("콜라", 1500, 3);

        Customer customer = new Customer("성민");

        System.out.println("상품명 : " + product.getName());
        System.out.println("가격 : " + product.getPrice());
        System.out.println("재고 : " + product.getStock());

        
        for (int i = product.getStock(); i >= 1; i-- ) {
            System.out.print("구매할 상품 : ");
            String name = sc.next();
        
            if (name.equals(product.getName())) {
                
                    customer.buy(product);
                } else {
                System.out.println("상품이 없습니다");
                
            }
        }

        System.out.println("--------------------");
        System.out.println("상품명 : " + product.getName());
        System.out.println("가격 : " + product.getPrice() + "원");
        System.out.println("재고 : " + product.getStock() + "개");

    }// main
}
