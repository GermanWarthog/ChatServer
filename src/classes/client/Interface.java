package classes.client;
import javax.swing.*;

public class Interface {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;
    private Chat chatServer;
    private String username;
    
    public Interface(Chat chatServer, String username) {
        this.chatServer = chatServer;
        
        this.username = username;
        this.chatServer.send(this.username + ": ist dem Chat beigetreten!");
        
        this.frame = new JFrame("Chat Server");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 600);

        JPanel panel = new JPanel();

        this.textField = new JTextField(40);
        panel.add(this.textField);

        JButton button = new JButton("Senden");
        panel.add(button);

        this.textArea = new JTextArea(30, 50);
        this.textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(this.textArea);
        panel.add(scrollPane);

        button.addActionListener(e -> {
            String text = this.textField.getText();
            this.textField.setText("Sende..");
            this.chatServer.send(this.username + ": " + text);
        });

        this.frame.add(panel);
        this.frame.setVisible(true);
    }

    public void AddMessage(String message) {
        this.textField.setText("");
        this.textArea.append(message + "\n");
    }

    public void CloseAll() {
        this.frame.setVisible(false);
    }
}