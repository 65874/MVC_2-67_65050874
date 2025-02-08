import java.awt.Font;
import javax.swing.*;

public class View {
    private Controller controller;

    private JFrame frame;
    private JPanel panel;  

    private JTextField inputField;
    private JTextArea outputField;

    public View(Controller controller, String programName) {
        this.controller = controller;

        frame = new JFrame(programName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        panel = new JPanel();
        panel.setLayout(null); 

        JLabel inputLabel = new JLabel("Input Food code with a 6-digit number. ");
        inputLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        inputLabel.setBounds(10, 10, 500, 30);
        panel.add(inputLabel);

        inputField = new JTextField(10);
        inputField.setBounds(10, 50, 1500, 30);
        panel.add(inputField);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        submitButton.setBounds(10, 90, 300, 30);
        submitButton.addActionListener(e -> handleSubmit());
        panel.add(submitButton);

        outputField = new JTextArea();
        outputField.setFont(new Font("Calibri", Font.PLAIN, 20));
        outputField.setBounds(10, 140, 1500, 1000);
        outputField.setEditable(false);  
        outputField.setLineWrap(true);
        outputField.setWrapStyleWord(true);

        panel.add(outputField);
        
        frame.add(panel);
        frame.setVisible(true);
    }


    // Controller เรียกเพื่อแสดงผลลัพธ์
    public void updateOutputField(String output) {
        outputField.setText(output);
    }

    // เมื่อกดปุ่ม Submit ในหน้ารับ Input ส่งข้อมูลไปยัง Controller
    private void handleSubmit() {
        String inputText = inputField.getText();  // รับข้อความจาก Input Field
        controller.takeInput(inputText);  // ส่งข้อมูลไปที่ Controller
    }

}
