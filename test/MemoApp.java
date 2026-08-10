import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MemoApp extends Frame {

    private final TextField textField;
    private final TextArea textArea;

    public MemoApp() {
        super("메모 앱");

        setBounds(300, 500, 300, 500);
        setLayout(new BorderLayout(5, 5));

        textField = new TextField(20);
        textArea = new TextArea("", 10, 20, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setEditable(false);

        Button confirmButton = new Button("확인");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText().trim();
                if (!input.isEmpty()) {
                    textArea.append(input + System.lineSeparator());
                    textField.setText("");
                }
                textField.requestFocus();
            }
        });

        Panel topPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        topPanel.add(textField);
        topPanel.add(confirmButton);

        add(topPanel, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MemoApp();
    }
}
