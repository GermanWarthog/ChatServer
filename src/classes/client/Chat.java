package classes.client;

import java.util.concurrent.TimeUnit;
import imports.Client;

public class Chat extends Client {
    private Interface chatInterface; 
    private String username;
    
    public Chat(String IP, int Port, String username) {
        super(IP, Port);
        this.username = username;
        this.chatInterface = new Interface(this, username);
        if (!this.isConnected()) {
            System.out.println("Verbindung zum Server konnte nicht aufgebaut werden!");
            this.chatInterface.CloseAll();
            System.exit(0);
        }
    };

    @Override
    public void processMessage(String pMessage) {
        do {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (this.chatInterface == null);
        String[] args = pMessage.split(":");
        System.out.println(args[0]);
        if (args[0].equals("TriggerClientEvent")) {
            switch (args[1]) {
                case "Connected":
                    this.chatInterface.AddMessage(this.username + ": ist dem Chat Server beigetreten!");
                default:
                    return;
            }
        }
        this.chatInterface.AddMessage(pMessage);
    }
}
