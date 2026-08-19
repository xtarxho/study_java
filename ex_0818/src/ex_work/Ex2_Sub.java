package ex_work;

public class Ex2_Sub {
    
    public void findActor(String[][] actor, String name, int cnt){

        for( int i = 0; i < actor.length; i++ ){

            if (actor[i][0].equals("[" + name + "]")) {
                
                
                for( int j = 0; j < actor[i].length; j++ ){
    
                    System.out.println(actor[i][j]);
                    
                }//inner
            }else{

                cnt++;

                if (cnt == actor.length) {
                    
                    System.out.println("배우정보가 없음");
                }
            }


                
                
            }//outer

    }//findActor



}
