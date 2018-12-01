import java.util.*;

public class MenuF34 {
    Dates dateFeature;

    //TODO F3 SORT F3: Sorting uninsured vehicles based on plates-number
    //The application, if requested, can provide the uninsured vehicles based
    //on the plate numbers, ordered in alphanumerical natural order.
    //TIP: try to implement the sorting solution inside the Java application,
    //and avoid a  DB-prepared ordered result.
    public void platesOrder(List<Vehicle> vehiclesInf, List<Vehicle> vehiclesInfDB,
                            int readFrom, int writeTo) throws Exception {

        ExportFile fileExport = new ExportFile();
        if (writeTo==2){fileExport.writeToCsv("SortedPlateNumbers.csv", "PlateNumber");}
        if (readFrom==1) {//************************CsvParser*********************//
            Collections.sort(vehiclesInf, new Comparator<Vehicle>() {
                @Override
                public int compare(Vehicle o1, Vehicle o2) {
                    return o1.getPlateNumber().compareTo(o2.getPlateNumber());  }  });
            System.out.print("The car's plates registration sorting order selected is:\n\t\t\t  [Alpha-Numerical]");
            for (Vehicle V : vehiclesInf) {
                Dates dateFeature = new Dates();
                if (dateFeature.Date2afterDate1(V.getFinishDayInsu())) {
                    if (writeTo==1) {
                        System.out.print("\n\t\t\t\t "+"|"+V.getPlateNumber()+"|");
                    }else if (writeTo==2){
                        fileExport.appendToCsv("SortedPlateNumbers.csv", ",\n"+V.getPlateNumber());
                    }
                }
            }
        }
        else if(readFrom==2){ //************************DB*********************//
           Collections.sort(vehiclesInfDB, new Comparator<Vehicle>() {
                @Override
                public int compare(Vehicle o1, Vehicle o2) {
                    return o1.getPlateNumber().compareTo(o2.getPlateNumber());  }  });
            System.out.print("The car's plates registration sorting order selected is:\n\t\t\t  [Alpha-Numerical]");

            for (Vehicle V : vehiclesInfDB) {
                Dates dateFeature = new Dates();
                if (dateFeature.Date2afterDate1(V.getFinishDayInsu())) {
                    if (writeTo==1) {
                        System.out.print("\n\t\t\t\t "+"|"+V.getPlateNumber()+"|");
                    }
                    else if (writeTo==2){
                        fileExport.appendToCsv("SortedPlateNumbers.csv", ",\n"+V.getPlateNumber());
                    }
                }
            }
        }
    }

    //TODO F4
    public void fineCalcByOwner(List<Vehicle> vehiclesInf,List<Vehicle> vehiclesInfDB,
                                int readFrom, int writeTo, double fine) throws Exception {

        ExportFile fileExport = new ExportFile();
        if (writeTo==2){ fileExport.writeToCsv("fineCalcByOwner.csv", "No match found");
        System.out.println("Csv will be generated");}

        Map<String, Integer> fines = new HashMap<>();
        int i=1;

        if (readFrom==1) {//************************CsvParser*********************//
            for (Vehicle V : vehiclesInf) {
                Dates dateFeature = new Dates();
                if (dateFeature.Date2afterDate1(V.getFinishDayInsu())) {
                    if (fines.containsKey(V.getOwnerName())) {
                        fines.computeIfPresent(V.getOwnerName(), (k, v) -> v + 1);
                    } else {
                        fines.put(V.getOwnerName(), i);
                    }
                }
            }
        }
        else if(readFrom == 2) { //************************DB*********************//
            for (Vehicle V : vehiclesInfDB) {
                Dates dateFeature = new Dates();
                if (dateFeature.Date2afterDate1(V.getFinishDayInsu())) {
                    if (fines.containsKey(V.getOwnerName())) {
                        fines.computeIfPresent(V.getOwnerName(), (k, v) -> v + 1);
                    } else {
                        fines.put(V.getOwnerName(), i);
                    }
                }
            }
        }
        int c=0; //counterfor write=2 and fine=0 case
        for ( Map.Entry<String, Integer> entry : fines.entrySet()) {
            String key = entry.getKey();
            Integer tab = entry.getValue();

            if (writeTo==1){ //writes to cosole
                if (fine!=0){ System.out.println("The owner " +key+ " is obligated to pay a fine of: " + tab*fine+" €.");}
                else{ System.out.println("No user has to pay a fine!"); writeTo=0; }
            }
            else if (writeTo==2){   //writes to csv
                if (fine!=0){
                    if(c==0){fileExport.writeToCsv("fineCalcByOwner.csv", "CarOwner,FineToPay\n");c++;}
                    fileExport.appendToCsv("fineCalcByOwner.csv", key+ "," + tab*fine+"\n");}
            }  }  }  }