package ex2_choice;

import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChoiceListener implements ItemListener{
    
    Label resLabel;

    public ChoiceListener(Label resLabel){
        this.resLabel = resLabel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        String str = (String)e.getItem();

                if (!str.equals("지역선택")) {
                    resLabel.setText(str + "을(를) 선택");
                }else{
                    resLabel.setText("");
                }

    }

}
