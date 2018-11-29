import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {


        List<Vehicles> vehiclesInfDB = new LinkedList<Vehicles>();

        DbConnector myDatabase = new DbConnector();
        try {
            myDatabase.dbcall();


        } catch (SQLException e) {
            System.out.println("Something went wrong please retry and check out the db and/or csv file");
            e.printStackTrace();
        }
        int i = 0;
        for(i=0; i<myDatabase.columnFullName.size(); i++) {
            //System.out.println("hello111111111111");
            vehiclesInfDB.add(new Vehicles(myDatabase.columnFullName.get(i), myDatabase.columnCarPlate.get(i),
                    myDatabase.columnCarModel.get(i), myDatabase.columnDate.get(i)));
        }
        //for (Vehicles V : vehiclesInfDB) {
       //     System.out.println(V.getFinishDayInsu() + "aaaaaaaaaaaaaaaaaaa");
       // }

        String file = "example.csv";
        // TODO read csv file
        List<String[]> dataListEggrafes = CSV.read(file);
        List<Vehicles> vehiclesInf = new LinkedList<Vehicles>();


        for (String[] csvR : dataListEggrafes) {

            vehiclesInf.add(new Vehicles(csvR[0], csvR[1], csvR[2], csvR[3]));
        }

            Menu menu = new Menu();
            Scanner keyboard = new Scanner(System.in);
            String licensePlate;
            int choice;
            int readFrom;
            int writeTo;
          //  int days;


            do {
                System.out.println("---Select Functionality to perform:\n*1 Vehicle Insurance status\n*2 Forecoming Expiries\n*3 Expiries by plate\n*4 Fine Calculation by Owner");

                try {
                    choice = keyboard.nextInt();
                } catch (InputMismatchException exception) {
                    System.out.println("You have not typed an Integer so the program shall exit..\nRe execute and be careful.");
                    return; //if user promps wrong number the program closes all together
                }


                switch (choice) {
                    case 1:
                        do{
                        System.out.println("Provide a license plate with the correct format (Format: ABC-1234):");
                        licensePlate = keyboard.next();
                        }while (!menu.validLicensePlate(licensePlate));
                        System.out.println("--Where you want to bring data from\n*1 CSV\n*2 DB");
                        readFrom = keyboard.nextInt();
                        System.out.println("--Where do you want to write the data\n*1 Console\n*2 CSV");
                        writeTo = keyboard.nextInt();
                        menu.vehicleInsuranceStatus(vehiclesInf, licensePlate, vehiclesInfDB, readFrom, writeTo);
                        break;
                    case 2:
                        System.out.println("--Where you want to bring data from\n*1 CSV\n*2 DB");
                        readFrom = keyboard.nextInt();
                        System.out.println("--Where do you want to write the data\n*1 Console\n*2 CSV");
                        writeTo = keyboard.nextInt();
                        menu.forecomingExpiries(vehiclesInf, vehiclesInfDB, readFrom, writeTo);
                        break;
                    case 3:
                        System.out.println("--Where you want to bring data from\n*1 CSV\n*2 DB");
                        readFrom = keyboard.nextInt();
                        System.out.println("--Where do you want to write the data\n*1 Console\n*2 CSV");
                        writeTo = keyboard.nextInt();

                        menu.platesOrder(vehiclesInf, vehiclesInfDB, readFrom, writeTo);
                        break;
                    case 4:
                        System.out.println("--Where you want to bring data from\n*1 CSV\n*2 DB");
                        readFrom = keyboard.nextInt();
                        System.out.println("--Where do you want to write the data\n*1 Console\n*2 CSV");
                        writeTo = keyboard.nextInt();
                        System.out.println("Type the amount of the fine(for non integer values please separate decimal-floating with comma" +
                                " (e.g. 12,2 or 344,532 or etc.)");
                        //try catch >>
                        try {
                            double fine = keyboard.nextInt();
                            menu.fineCalcByOwner(vehiclesInf, vehiclesInfDB,readFrom, writeTo, fine);
                        } catch (InputMismatchException exception) {
                            System.out.println("You have typed something wrongly. \nRe execute and be careful.");
                            return; //if user promps wrong number the program closes all together
                        }

                        break;

                    default:
                        System.out.println("Please input a valid number");
                        break;
                }
            } while ((choice != 1) && (choice != 2) && (choice != 3) && (choice != 4));


        }


}

