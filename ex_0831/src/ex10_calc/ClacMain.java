package ex10_calc;

import java.awt.*;
import java.awt.event.WindowAdapter;

public class ClacMain {
    public static void main(String[] args) {
        
        Frame f = new Frame("계산기");
        f.setBounds(500, 200, 260, 300);
        f.setLayout(null);//자동배치 끄기

        Font font = new Font("", Font.BOLD, 20);
        Label lb = new Label("0"); //숫자로 찍고싶어도 문자열로 넣어야한다
        lb.setBounds(10,180,180,100);
        lb.setFont(font);

        //계산기의 모든 버튼이 참조할 감지자 만들기
        MyListener listener = new MyListener();
        listener.setLb(lb);

        //숫자버튼들
        Button[] nums = new Button[10];
        for( int i = 0; i < nums.length; i++ ){

            nums[i] = new Button(String.valueOf(i)); // -> 편하게 하고싶으면 ("" + i) 사용 

            if ( i >= 0 && i <= 4 ) {
                nums[i].setBounds(i * 50 + 10, 40, 40, 40);
            }else{
                //5~9버튼의 위치
                nums[i].setBounds((i-5)*50+10,90,40,40);
            }

            //숫자버튼들에게 감지자 등록
            nums[i].addActionListener(listener);

            //프레임에 숫자 버튼들을 추가
            f.add(nums[i]);

            

        }//for

        //연산자 버튼들
        Button[] opers = new Button[5];
        String[] opStr = {"+", "-", "*", "/", "="};
        for(int i = 0; i < opers.length; i++){

            opers[i] = new Button(opStr[i]);
            opers[i].setBounds(i*50+10,140,40,40);
            
            //연산자 버튼들에게 이벤트 감지자 등록
            opers[i].addActionListener(listener);
            f.add(opers[i]);

        }//for

        //C버튼
        Button btnC = new Button("C");
        btnC.setBounds(200,210,50,50);
        btnC.addActionListener(listener);

        //프레임에 C버튼을 추가
        f.add(btnC);


        //프레임에 레이블 추가
        f.add(lb);


        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            };

        });

    }//main
}
