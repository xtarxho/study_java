package ex5_label;

public class Ex2_label {
    public static void main(String[] args) {
        
        my : for( int i = 1; i <= 5; i++ ){

            switch (i) {
                case 1:
                    System.out.println("case 1");
                    break my; 
            }

            System.out.println("안녕");

        }


    }//main
}
