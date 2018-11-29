import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.Scanner;

public class DbConnector {
    ArrayList<String> columnFullName = new ArrayList<String>();
    ArrayList<String> columnCarPlate = new ArrayList<String>();
    ArrayList<String> columnDate = new ArrayList<String>();
   ArrayList<String>  columnCarModel = new ArrayList<String>();


    //            vehiclesInf.add(new Vehicles(csvR[0], csvR[1], csvR[2], csvR[3]));

    public void dbcall() throws SQLException {
        Scanner scanner = new Scanner(System.in); //so as to select insert,update,delete
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;



        try {
            // connect to database- change password/username as per ur pc
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "aekara21");

            //System.out.println("Database connection successful!\n");

            // Create a statement
            myStmt = myConn.createStatement();

            //Insert values(id is set to Auto increment so no need to add value):
         //   System.out.println("Gimme me a number: 1 to Insert some predefined data(or press anything else to continue):");
           // try {
          //      int userInput = scanner.nextInt();
            //    if (userInput == 1) {
            //        // System.out.println("I am inserting successfully ");
           //         int newValues = myStmt.executeUpdate("insert into project1.car_plate_data" +
           //             "(FullName,PlateNumber,CarModel,ExpirationDatestamp)" +
           //             "values " + "('Akis Pap','ABC-1243', 'Mercedes','12-aug-2202')");
           //     }
           // }catch (InputMismatchException exception) {System.out.println("Wrong Input.. I'll display all the db's records regarding\nFullname, Car Plate");}

            // Execute SQL query
            myRs = myStmt.executeQuery("SELECT * FROM dbproject1.car_plate_data;");


            // Process result set
            while (myRs.next()) {
                //System.out.println(myRs.getString("FullName") + ", " + myRs.getString("PlateNumber"));
                columnFullName.add(myRs.getString("FullName"));
                columnCarPlate.add(myRs.getString("PlateNumber"));
                columnDate.add(myRs.getString("ExpirationDatestamp"));
                columnCarModel.add(myRs.getString("CarModel"));
            //   vehiclesInfDB2.add(new Vehicles(myRs.getString("FullName"), myRs.getString("PlateNumber"), myRs.getString("CarModel"), myRs.getString("ExpirationDatestamp")));

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }


}

