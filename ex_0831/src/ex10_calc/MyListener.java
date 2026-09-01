package ex10_calc;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener {

    private String num1 = "";
    private String num2 = "";
    private String op = "";
    private int res1 = 0; // 결과1
    private float res2 = 0; // 결과2(나누기 전용)
    private int phase = 0;

    private Label lb;

    public void setLb(Label lb) {
        this.lb = lb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "+":
            case "-":
            case "*":
            case "/":
                phase = 1;
                op = e.getActionCommand();
                lb.setText(num1 + " " + op + " ");
                break;

            case "=":
                phase = 0;
                switch (op) {
                    case "+":
                        res1 = Integer.parseInt(num1) + Integer.parseInt(num2);
                        break;
                    case "-":
                        res1 = Integer.parseInt(num1) - Integer.parseInt(num2);
                        break;
                    case "*":
                        res1 = Integer.parseInt(num1) * Integer.parseInt(num2);
                        break;
                    case "/":
                        res2 = Float.parseFloat(num1) / Float.parseFloat(num2);
                        break;
                }// switch

                //결과 출력
                if ( op.equals("/") ) {
                    lb.setText("" + res2);
                }else{
                    lb.setText("" + res1);
                }//if
                break;

            case "C":
                num1 = "";
                num2 = "";
                op = "";
                res1 = 0;
                res2 = 0;
                phase = 0;
                lb.setText("0");
                break;

            default: // 숫자버튼 클릭을 감지한다
                if (phase == 0) { // 앞의 수 입력받겠다는 뜻
                    num1 += e.getActionCommand();
                    lb.setText(num1);
                } else { // 뒤에 숫자 입력 받겠다는 뜻
                    num2 += e.getActionCommand();
                    lb.setText(num1 + " " + op + " " + num2);

                }
                break;

        }// switch

    }

}
