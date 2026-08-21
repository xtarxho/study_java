package ex9_work;

public class WorkMain {
    public static void main(String[] args) {
        
        //0829에도 같은 문제 나올예정

        //1~45사이의 중복되지 않는 난수 6개를 출력하는
        //로또번호생성기

        int[] lotto = new int[6];
        
            WorkSub ws = new WorkSub();
            ws.myLotto(lotto);

        for(int i = 0; i < lotto.length; i++){
            System.out.print( lotto[i] + " " ); //얕은 복사 때문에 주소값이 같아서 출력이 가능하다
        }




    }//main
}
