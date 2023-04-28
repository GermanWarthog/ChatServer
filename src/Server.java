import classes.server.SQL;
import classes.server.ServerProcess;

public class Server {
    public static void main(String[] args) {
        new ServerProcess(8080, new SQL("2.58.113.167", 3306, "test", "admin", "aun4pie6oshe0Ahy6keizaiquez8Ooki"));
    }
}
