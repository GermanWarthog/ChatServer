package classes.server;

import imports.DatabaseConnector;
import imports.QueryResult;

public class SQL{
    private DatabaseConnector DB;

    public SQL(String pIP, int pPort, String pDatabase, String pUsername, String pPassword) {
        try {
            this.DB = new DatabaseConnector(pIP, pPort, pDatabase, pUsername, pPassword);
            System.out.println("Server ist mit der Datenbank verbunden!");
            this.DB.executeStatement("INSERT INTO `Chat` (`owner`, `content`) VALUES ('Test', 'Test')");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // FIXME: Queries werden nicht vernünftig ausgeführt!
    public String[][] select(String query) {
        try {
            this.DB.executeStatement(query);
            QueryResult result = this.DB.getCurrentQueryResult();
            String[][] data = result.getData();
            return data;
        } catch (Exception e) {
            System.out.println("Error: " + e  + "\r\n" + this.DB.getErrorMessage());
        }
        return null;
    }

    public void insert(String query) {
        try {
            this.DB.executeStatement(query);
        } catch (Exception e) {
            System.out.println("Error: " + e  + "\r\n" + this.DB.getErrorMessage());
        }
    }
}
