import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        CommandLineInterface  commandLineGui = new CommandLineInterface();
        DbConnector myDatabase = new DbConnector();
        myDatabase.dbcall(); //start db connection

        String file = "example.csv"; //file directory:pwd
        commandLineGui.userPromt(file,myDatabase);
    }
}