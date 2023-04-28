package classes.server;
import imports.*;

public class ServerProcess extends Server {
    SQL MySQL;

    public ServerProcess(int pPort, SQL MySQL) {
        super(pPort);
        this.MySQL = MySQL;
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        if (!this.isOpen()) {
            return;
        }
        // String messageHistory;
        String[][] messages = this.MySQL.select("SELECT * FROM Chat WHERE 1");
        if (messages != null) {
            for (String[] row : messages) {
                for (String message : row) {
                    System.out.println("Nachricht:");
                    System.out.println(message + "\t");
                }
            }
        }
        this.send(pClientIP, pClientPort, "Verbunden");
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        System.out.println("Nachricht wurde empfangen: " + pClientIP + " " + pClientPort + " " + pMessage);
        this.sendToAll(pMessage);
        String[] args = pMessage.split(":");
        this.MySQL.insert("INSERT INTO CHAT (owner, content) VALUES (" + args[0] + ", " + args[1] +")");
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        this.sendToAll("Verbindung zum Server wurde abgebrochen. Grund: Server wurde geschlossen.");
    }
}
