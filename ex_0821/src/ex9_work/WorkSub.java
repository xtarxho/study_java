package ex9_work;

import java.util.Random;

public class WorkSub {

    Random rnd = new Random();
    
    public void myLotto(int[] lotto){ //얕은 복사

    out : for( int i = 0; i < lotto.length; ){
        lotto[i] = rnd.nextInt(45) + 1;
        
        for( int j = 0; j < i; j++ ){
            
            if (lotto[i]==lotto[j]) {
                continue out;
            }
        }
        //System.out.print(lotto[i] + " ");
        i++;
    }

}
}
