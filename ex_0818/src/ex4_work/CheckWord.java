package ex4_work;

public class CheckWord {
    
    public String check( String str ){

        String rev = "";

        for( int i = str.length()-1; i >= 0; i-- ){
            rev += str.charAt(i);                      
            }//for

            return rev;

    }


}
