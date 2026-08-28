package ex2_work;

public class WorkSub {
    
    public boolean isPrime(int num){

        int i = 2;
        for( ; i <= num; i++ ){
            if (num % i == 0) {
                break;
            }
        }

        if (i == num) 
            return true;
        else
            return false;
        

    }//isPrime()


}
