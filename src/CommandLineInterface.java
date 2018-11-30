import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface{

    public void userPromt(String file, DbConnector myDatabase) {
    String licensePlate;
    int readFrom,writeTo,choice=0;
    List<Vehicles> vehiclesInfDB = new LinkedList<Vehicles>();
    List<String[]> dataListEggrafes = CsvParser.read(file);
    List<Vehicles> vehiclesInf = new LinkedList<Vehicles>();

    for (int i = 0; i < myDatabase.columnFullName.size(); i++) {
      vehiclesInfDB.add(new Vehicles(myDatabase.columnFullName.get(i), myDatabase.columnCarPlate.get(i),
        myDatabase.columnCarModel.get(i), myDatabase.columnDate.get(i)));  }

    for (String[] csvR : dataListEggrafes) {vehiclesInf.add(new Vehicles(csvR[0], csvR[1], csvR[2], csvR[3]));}

    MenuF12 menuF12 = new MenuF12();
    MenuF34 menuF34 = new MenuF34();

    Scanner keyboard = new Scanner(System.in);

  do{
      System.out.println("---Select Functionality to perform:\n*1 Vehicle Insurance status\n*2 Forecoming Expiries" +
              "\n*3 Expiries by plate\n*4 Fine Calculation by Owner");
        try {choice = keyboard.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("You have not typed an Integer so the program shall exit..\nRe execute and be careful.");
            System.exit(0); //terminate execution for wrong user input
        }
        switch (choice) {
            case 1:
                do{
                 System.out.println("Provide a license plate with the correct format (Format: ABC-1234):");
                 licensePlate = keyboard.next();
                }while (!menuF12.validLicensePlate(licensePlate));
                 System.out.println("--Where you want to bring data from\n*1 Csv\n*2 DB");
                 readFrom = keyboard.nextInt();
                 System.out.println("--Where do you want to write the data\n*1 Console\n*2 Csv");
                 writeTo = keyboard.nextInt();
                 try { menuF12.vehicleInsuranceStatus(vehiclesInf, licensePlate, vehiclesInfDB, readFrom, writeTo);
                 } catch (Exception e) {e.printStackTrace();  }
                 break;
            case 2:
                System.out.println("Type the days you want to test");
                int days = keyboard.nextInt();
                System.out.println("--Where you want to bring data from\n*1 Csv\n*2 DB");
                readFrom = keyboard.nextInt();
                System.out.println("--Where do you want to write the data\n*1 Console\n*2 Csv");
                writeTo = keyboard.nextInt();
                try { menuF12.forecomingExpiries(vehiclesInf, vehiclesInfDB, readFrom, writeTo, days);
                } catch (Exception e) { e.printStackTrace();  }
                break;
            case 3:
                System.out.println("--Where you want to bring data from\n*1 Csv\n*2 DB");
                readFrom = keyboard.nextInt();
                System.out.println("--Where do you want to write the data\n*1 Console\n*2 Csv");
                writeTo = keyboard.nextInt();
                try { menuF34.platesOrder(vehiclesInf, vehiclesInfDB, readFrom, writeTo);
                } catch (Exception e) { e.printStackTrace();  }
                break;
            case 4:
                System.out.println("--Where you want to bring data from\n*1 Csv\n*2 DB");
                readFrom = keyboard.nextInt();
                System.out.println("--Where do you want to write the data\n*1 Console\n*2 Csv");
                writeTo = keyboard.nextInt();
                System.out.println("Type the amount of the fine(for non integer values please separate" +
                        " decimal-floating with a DOT" +" (e.g. 12.2 or 344.532 or etc.)");
                try {
                  double fine = keyboard.nextDouble();
                  try {menuF34.fineCalcByOwner(vehiclesInf, vehiclesInfDB,readFrom, writeTo, fine);
                  } catch (Exception e) { e.printStackTrace();  }
                } catch (InputMismatchException exception) {
                    System.out.println("You have typed something wrongly. \nRe execute and be careful.");
                    System.exit(0); //terminate execution for wrong user input
                }
                break;
            default:
                System.out.println("Please input a valid number");
                break;
        } } while ((choice != 1) && (choice != 2) && (choice != 3) && (choice != 4)); }}