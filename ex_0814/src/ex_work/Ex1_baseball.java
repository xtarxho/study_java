package ex_work;

import java.util.Random;
import java.util.Scanner;

public class Ex1_baseball {
    public static void main(String[] args) {

        //주석을 달아서 금요일에 제출해야됨

        //1 ~ 9 사이의 중복되지 않는 난수를 3개를 받아서 맞추기
        //결과
        // 세자리 수를 입력하세요(예:123) - 123
        // 1Strike, 1Ball
        // --------------------------
        // 세자리 수를 입력하세요(예:123) - 567
        // OUT!!
        // --------------------------
        // 세자리 수를 입력하세요(예:123) - 214
        // 0Strike, 3Ball
        // --------------------------
        // 세자리 수를 입력하세요(예:123) - 142
        // 4회 정답 !! - 142

        Scanner sc = new Scanner(System.in); //입력받을 값을 출력할 수 있게 도와주는 코드

        int[] com = new int[3]; //int배열 com은 새로운 주소로 3개의 방을 만든다 
        int[] user = new int[3]; //int배열 user는 새로운 주소로 3개의 방을 만든다
        outer : for( int i = 0; i < com.length;  ){ //i는 0이고 com.length(3)보다 작으면 계속 for문을 실행 실행하는 이유는 3개의 com 숫자를 출력받아야해서

            com[i] = new Random().nextInt( 9) + 1;  //com에게 9개의 숫자중에서 랜덤으로 뽑는다 

            //중복값 비교하는 for문을 하나 더 만든다
            for( int j = 0; j < i; j++ ){ //com[i]와 com[j]의 값을 비교해서 중복값이 나오면 안되기 때문에 for문을 하나 더 만들어서 비교

                if ( com[i] == com[j] ) { //if문을 통해서 com[i]와 com[j]가 같은지 비교하는 조건식을 만든다
                    continue outer; //com[i]와 com[j]가 같다면 다시 바깥 for문으로 가서 새로운 숫자를 랜덤으로 출력받는다
                }


            }//inner
            i++; //i의 값을 1 추가한다. 
            


        }//outer
        System.out.println("정답 : " + com[0] + com[1] + com[2]); //위에서 각자 다른 숫자 3개를 출력해주는 코드

        int cnt = 0; //몇회만에 정답을 맞추는지 셀 수 있게 도와주는 코드

        while(true){  //내가 입력한 숫자 3개와 랜덤으로 나온 숫자 3개가 맞는지 확인시켜주는 반복문

            cnt++; //한사이클 돌때마다 1씩 더해줌으로써 몇번만에 맞추는지 알려주는 변수

            System.out.print( "입력(예:123) : " ); // 입력 : 이라고 출력해준다

            int number = sc.nextInt(); //내가 숫자 3개를 입력하게 해주는 코드

            user[0] = number / 100;  //내가 입력한 숫자 3개에서 0번째 자리에 있는 숫자를 구하는 식. 만약 내가 324를 입력했으면 324 나누기 100이니까 몫인 3이 0번째 자리에 들어간다 
            user[1] = number / 10 % 10; // 1번째 자리에 있는 숫자를 구하는 식 
            user[2] = number % 10; // 2번째 자리에 있는 숫자를 구하는 식 

            int strike = 0; //스트라이크 횟수를 담을 변수
            int ball = 0; // 볼 횟수를 담을 변수

            for( int i = 0; i < user.length; i++ ){  //com(랜덤 숫자) 숫자와 user(내가 입력한 숫자)를 비교하는 for문 
                //i가 0이고 user.length는 3이다 조건식이 참이므로 안쪽 for문으로 들어간다
                for( int j = 0; j < user.length; j++ ){ //j는 0이고 user.length도 3이므로 밑에 있는 if문을 실행하게 된다

                    if ( i == j ) {  //i는 0이고 j도 0이므로 식이 참이되고 안에 있는 if문을 실행하게 되고
                        if ( com[i] == user[i] ) // com[i]와 user[i]가 같으면 스트라이크를 1 증가시킨다 -> 랜덤으로 나온 첫번째 숫자와 내가 입력한 첫번째 숫자 비교를 3번째 숫자까지 반복
                            strike++;
                        
                    }else{
                        if( com[i] == user[j] ) // 세자리 숫자중에서 같은 숫자는 있는데 위치가 다를 경우는 ball을 1 증가시킨다
                            ball++;
                    }

                }
                
                

            }//for

            //정답처리
            if (strike == 3) { //스트라이크가 3이랑 같으면 밑에 코드를 실행시킨다
                System.out.println("정답!! - " + com[0] + com[1] + com[2]); //스트라이크가 3이랑 같다는 뜻은 정답을 맞췄다는 뜻이므로 정답 숫자를 출력시킨다
                System.out.println(cnt + "회 클리어"); //아까 cnt에 담은 변수를 출력해 몇번만에 맞췄는지 알려준다
                break; //맞췄으므로 if문을 빠져나가고 실행을 중단한다
            }else{ //맞추지 못했다면 밑에 if문을 실행한다

                if ( strike > 0 || ball > 0 ) { //스트라이크가 0보다 크거나 볼이 0보다 크면 
                    System.out.printf( "%d Strike, %d Ball\n", strike, ball ); //아까 저장해둔 변수 strike와 ball을 출력해준다
                }else{// 하나라도 맞추지 못했다면

                    System.out.println("OUT"); // out이라는 문자열을 출력한다

                }

            }

            System.out.println("--------------------------------"); //구분선

        }//while








        
    }// main
}
