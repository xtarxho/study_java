package ex9_list;

public class ListSub {
    
    private String name;
    
    private int kor; //국어
    private int eng; //영어

    private int total; //총점
    private float avg; //평균

    public ListSub( String name, int kor, int eng ){
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        total = kor + eng;
        avg = total / 2f;
    }

    public float getAvg(){
        return avg;
    }

    public void ListSubinfo(){
        System.out.printf( "%s\t국어 : %d\t영어 : %d\t총점 : %d\t평균 : %.1f\n", name, kor, eng, total, avg );
    }
    
}
