import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        CommandLineInterface  commandLineGui = new CommandLineInterface();
        DbConnector myDatabase = new DbConnector();
        try { myDatabase.dbcall(); //start db connection
        } catch (SQLException e) { //e.printStackTrace();
            System.out.println("Something went wrong please retry and check out the db connection\n");
        }
        String file = "example.csv"; //file directory:pwd
        commandLineGui.userPromt(file,myDatabase);
    }
}