import java.util.List;

public class MenuF12 {
    private String licensePlate; //arxikh pinakida tou xrhsth
    Dates dateFeature;


    public boolean validLicensePlate(String licensePlate) {

        if (licensePlate.matches("[a-zA-Z][a-zA-Z][a-zA-Z]-[0-9][0-9][0-9][0-9]")) {
            //gia mono kefalaia : [A-Z]
            this.licensePlate = licensePlate;
            return true;
        } else {
            return false;
        }  }

    //TODO F1
    public void vehicleInsuranceStatus(List<Vehicle> vehicleInf, String licensePlate, List<Vehicle> vehiclesInfDB, int readFrom, int writeTo) throws Exception {

        ExportFile fileExport = new ExportFile();
        if (writeTo == 2) {
            fileExport.writeToCsv("vehicleInsuranceStatus.csv", "No match found");
            System.out.println("Csv file generated");
        } //if selected write to csv->create empty file with no match entry.If we find something overwrite this value...

        if (readFrom == 1) {//*********************FOR CsvParser*******************\\
            for (Vehicle V : vehicleInf) {
                    if (V.getPlateNumber().equals(licensePlate)) {    // sigkrisi Η ΗΜΕΡΟΜΗΝΙΑ Π ΤΕΛΕΙΩΝΕΙ Η ΑΣΦΑΛΕΙΑ
                    Dates dateFeature = new Dates();
                    if (dateFeature.Date2afterDate1(V.getFinishDayInsu())) { readFrom++;
                        if (writeTo == 1) { System.out.println("Your insurance ends at: " + V.getFinishDayInsu());
                        }//if(V.getFinishDayInsu() instanceof String){System.out.println("we got a string");}
                        else {fileExport.writeToCsv("vehicleInsuranceStatus.csv",
                                ( V.getFinishDayInsu()));
                        }
                    }
                }

            }
            if (readFrom == 1) {
                System.out.println("No match found");
            }
        } else if (readFrom == 2) {// *********************FOR DB*******************\\

            for (Vehicle V : vehiclesInfDB) {
                if (V.getPlateNumber().equals(licensePlate)) {    // test gia sigkrisi ktl
                    Dates dateFeature = new Dates();
                    if (dateFeature.Date2afterDate1(V.getFinishDayInsu())) {
                        readFrom++;
                        if (writeTo == 1) {
                            System.out.println("Your insurance ends at: " + V.getFinishDayInsu());
                        } else {
                            fileExport.writeToCsv("vehicleInsuranceStatus.csv", V.getFinishDayInsu());
                        }
                    }
                }
            }
            if (readFrom == 2) { System.out.println("No match found");  }  }  }

    //TODO F2 user input to days q variable dld
    public void forecomingExpiries(List<Vehicle> vehiclesInf, List<Vehicle> vehiclesInfDB, int readFrom, int writeTo, int days) throws Exception {

        ExportFile fileExport = new ExportFile();
        if (writeTo == 2) {
            fileExport.writeToCsv("forecomingExpiries.csv", "No match found");
            System.out.println("Csv file generated");
        } //if selected write to csv->create empty file with no match entry.If we find something overwrite this value...

        if (readFrom == 1) { //************************CsvParser*********************//
            for (Vehicle V : vehiclesInf) {
                Dates dateFeature = new Dates();
                if (!(dateFeature.Date2afterDate1(V.getFinishDayInsu()))) {  //checks if finishInsuranceDate is before current Date
                } else if ((dateFeature.Date2afterDate1(V.getFinishDayInsu()))) {  //checks if finishInsuranceDate is before current Date) {
                    if (dateFeature.daysBetween(V.getFinishDayInsu()) < days) {
                        if (writeTo == 1) {  System.out.println("The car's insurance with registration plate" +
                                    " number: " + V.getPlateNumber() + " is about to expire.");
                        } else if (writeTo == 2) {
                            if (readFrom == 1) {
                                fileExport.writeToCsv("forecomingExpiries.csv",
                                        "PlateNumber,\n" + V.getPlateNumber());
                                readFrom++;
                            }fileExport.appendToCsv("forecomingExpiries.csv", ",\n" + V.getPlateNumber());
                        }  }  }  }  }

        else if (readFrom == 2) {  //************************DB*********************//

            for (Vehicle V : vehiclesInfDB) {
                Dates dateFeature = new Dates();
                if ((!dateFeature.Date2afterDate1(V.getFinishDayInsu()))) {  //checks if finishInsuranceDate is before current Date
                } else if ((dateFeature.Date2afterDate1(V.getFinishDayInsu()))) {  //checks if finishInsuranceDate is before current Date) {
                    if (dateFeature.daysBetween(V.getFinishDayInsu()) < days) {
                        if (writeTo == 1) {
                            System.out.println("The car's insurance with registration plate number : "
                                    + V.getPlateNumber() + " is about to expire.");
                        } else if (writeTo == 2) {
                            if (readFrom == 2) {
                                fileExport.writeToCsv("forecomingExpiries.csv",
                                        "PlateNumber,\n" + V.getPlateNumber());
                                readFrom++;
                            }
                            fileExport.appendToCsv("forecomingExpiries.csv", ",\n" + V.getPlateNumber());
                        }  }  }  }  }  }  }