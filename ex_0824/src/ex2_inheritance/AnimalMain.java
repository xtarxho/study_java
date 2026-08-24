package ex2_inheritance;

public class AnimalMain {
    public static void main(String[] args) {
        
        Bear bear = new Bear();
        System.out.println("--곰--");
        System.out.println("눈 : " + bear.getEye());
        System.out.println("다리 : " + bear.getLeg());
        System.out.println("특징 : " + bear.merit);

        Elephant ele = new Elephant();
        System.out.println("--코끼리--");
        System.out.println("눈 : " + ele.getEye());
        System.out.println("다리 : " + ele.getLeg());
        System.out.println("특징 : " + ele.merit);

        Snake snake = new Snake();
        System.out.println("--뱀--");
        System.out.println("눈 : " + snake.getEye());
        System.out.println("다리 : " + snake.getLeg());
        System.out.println("특징 : " + snake.merit);



    }//main
}
