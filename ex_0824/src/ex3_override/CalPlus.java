package ex3_override;

public class CalPlus extends Calculator{
    

    @Override
    public int getResult(int n1, int n2) {
        return n1 + n2;
    }
}
