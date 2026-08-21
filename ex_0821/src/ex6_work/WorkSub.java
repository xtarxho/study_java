package ex6_work;

public class WorkSub {
    
    public boolean isPrime(int n){

        int i = 2;
        for( ; i <= n; i++ ){

            if (n % i == 0) {
                break; //왜 break로 for문을 빠져나오는지
            }

        }

        if (i == n) 
            return true;
        else
            return false;

    }

}
