package ex2_while;

public class Ex4_do_while {
    public static void main(String[] args) {
        //do-while문 : 
        //선 처리 후 비교 -> while문이랑 반대(선 비교 후 처리)
        
        int i = 9;

        do{ //1회전에만 선 처리한다 그 다음부터는 while문이랑 똑같다
            
            System.out.println(i);
            i++;  //선 처리

        }while( i <=10 ); //후 비교


    }//main
}
