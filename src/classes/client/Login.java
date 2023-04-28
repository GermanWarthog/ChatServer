package classes.client;

import javax.swing.*;
import java.lang.Thread;

public class Login {
    private JFrame frame;
    private JTextField textField;
    private String username;

    public Login() {
        this.frame = new JFrame();
        this.frame = new JFrame("Chat Server : Login");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(300, 300);

        JPanel panel = new JPanel();

        this.textField = new JTextField(20);
        panel.add(this.textField);

        JButton button = new JButton("Username Bestätigen");
        panel.add(button);

        button.addActionListener(e -> {
            String text = this.textField.getText();
            if (text.length() < 3) {
                this.textField.setText("Username sollte länger als 3 Zeichen sein!");
                try {
                    Thread.sleep(3000); 
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
                this.textField.setText("");
                return;
            }
            this.username = text;
            this.frame.setVisible(false);
            new Chat("localhost", 8080, this.getUsername());
        });

        this.frame.add(panel);
        this.frame.setVisible(true);
    }

    public String getUsername() {
        return this.username;
    }   

    public boolean isLogedIn() {
        return (this.username != null);
    }
}
