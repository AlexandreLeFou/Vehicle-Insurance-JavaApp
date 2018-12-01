import java.sql.*;
import java.util.ArrayList;

public class DbConnector {
    ArrayList<String> columnFullName = new ArrayList<String>();
    ArrayList<String> columnCarPlate = new ArrayList<String>();
    ArrayList<String> columnDate = new ArrayList<String>();
    ArrayList<String>  columnCarModel = new ArrayList<String>();

    public void dbcall() throws SQLException {

        try(
            // connect to database- change password/username as per your pc
            Connection  myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "bukomk5t1");
            Statement myStmt = myConn.createStatement(); // Create a statement
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM project1.car_plate_data;"); // Execute SQL query
            ){

            while (myRs.next()) { // Process result set
                columnFullName.add(myRs.getString("FullName"));
                columnCarPlate.add(myRs.getString("PlateNumber"));
                columnDate.add(myRs.getString("ExpirationDatestamp"));
                columnCarModel.add(myRs.getString("CarModel"));
            }
        } catch (SQLException e) {
            System.out.println("Soomething went wrong please retry and check out the db connection\n");//e.printStackTrace();
            System.exit(0); //terminate execution
        } catch (Exception exc) { exc.printStackTrace();
        }  }}

