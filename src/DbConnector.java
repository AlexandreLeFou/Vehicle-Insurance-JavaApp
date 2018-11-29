import java.sql.*;
import java.util.ArrayList;

public class DbConnector {
    ArrayList<String> columnFullName = new ArrayList<String>();
    ArrayList<String> columnCarPlate = new ArrayList<String>();
    ArrayList<String> columnDate = new ArrayList<String>();
    ArrayList<String>  columnCarModel = new ArrayList<String>();

    public void dbcall() throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // connect to database- change password/username as per your pc
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "aekara21");
            myStmt = myConn.createStatement(); // Create a statement
            myRs = myStmt.executeQuery("SELECT * FROM project1.car_plate_data;"); // Execute SQL query


            while (myRs.next()) { // Process result set
                columnFullName.add(myRs.getString("FullName"));
                columnCarPlate.add(myRs.getString("PlateNumber"));
                columnDate.add(myRs.getString("ExpirationDatestamp"));
                columnCarModel.add(myRs.getString("CarModel"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong please retry and check out the db connection\n");//e.printStackTrace();
            System.exit(0); //terminate execution
        } catch (Exception exc) { exc.printStackTrace();
        } finally { //closes all open interactions with db (connections etc.)
            if (myRs != null)   { myRs.close();  }
            if (myStmt != null) { myStmt.close();}
            if (myConn != null) { myConn.close();}
        }  }}

