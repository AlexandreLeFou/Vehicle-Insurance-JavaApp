import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnector {
    // assign to abstraction
    List<String> columnFullName = new ArrayList<>();
    ArrayList<String> columnCarPlate = new ArrayList<String>();
    ArrayList<String> columnDate = new ArrayList<String>();
    ArrayList<String> columnCarModel = new ArrayList<String>();

    private final String getAllVehiclesQuery = "SELECT * ";

    public void dbcall() throws SQLException {

        try (
                // connect to database- change password/username as per your pc
                // Property file for credentials
                Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "aekara21");
                Statement myStmt = myConn.createStatement(); // Create a statement
                ResultSet myRs = myStmt.executeQuery("SELECT * FROM project1.car_plate_data; "); // Execute SQL query
        ) {

            while (myRs.next()) { // Process result set
//                Vehicle vehicle = new Vehicle(
//                        myRs.getString("FullName"),
//                        myRs.getString("PlateNumber"),
//                        myRs.getString("ExpirationDatestamp"),
//                        myRs.getString("CarModel")
//                );
                columnFullName.add(myRs.getString("FullName"));
                columnCarPlate.add(myRs.getString("PlateNumber"));
                columnDate.add(myRs.getString("ExpirationDatestamp"));
                columnCarModel.add(myRs.getString("CarModel"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong please retry and check out the db connection\n");//e.printStackTrace();
            System.exit(0); //terminate execution
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}

