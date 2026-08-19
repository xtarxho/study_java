package ex3_class_array;

public class PersonMain {
    public static void main(String[] args) {
        
        Person[] p1 = new Person[2]; //Person 클래스를 가지고 배열을 만들려고 한다
        //클래스를 배열로 만들었다면 
        //반드시 각 index의 객체화를 해줘야 한다
        //생성자를 통해서 다시 배열을 만들어야 한다
        for( int i = 0; i < p1.length; i++ ){  //p1.length는  Person[] p1 = new Person[2]; 의 길이  -> 2
            p1[i] = new Person();
            

        }

        p1[0].setName("홍길동");
        p1[0].setAge(20);

        p1[1].setName("김길동");
        p1[1].setAge(25);

        //홍길동 / 20
        //김길동 / 25

        for( int i = 0; i < p1.length; i++ ){
            //System.out.println(p1[i].getName() + " / " + p1[i].getAge()); //내가 푼 방법
            System.out.printf("%s / %d\n", p1[i].getName(),p1[i].getAge()); //쌤이 알려주신 방법
        }

        
    }//main
}
