import java.text.SimpleDateFormat;
import java.util.*;

public class MenuF34 {

    //TODO F3 SORT F3: Sorting uninsured vehicles based on plates-number
    //The application, if requested, can provide the uninsured vehicles based
    //on the plate numbers, ordered in alphanumerical natural order.
    //TIP: try to implement the sorting solution inside the Java application,
    //and avoid a  DB-prepared ordered result.
    public void platesOrder(List<Vehicles> vehiclesInf, List<Vehicles> vehiclesInfDB, int readFrom, int writeTo) throws Exception {

        ExportFile fileExport = new ExportFile();
        if (writeTo==2){fileExport.writeToCsv("SortedPlateNumbers.csv", "PlateNumber");}

        //************************CsvParser*********************//
        if (readFrom==1) {

            Collections.sort(vehiclesInf, new Comparator<Vehicles>() {
                @Override
                public int compare(Vehicles o1, Vehicles o2) {
                    return o1.getPlateNumber().compareTo(o2.getPlateNumber());
                }
            });
            System.out.print("The car's plates registration sorting order selected is:\n\t\t\t  [Alpha-Numerical]");
            for (Vehicles V : vehiclesInf) {

                String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //timeStamp h shmerini imerominia
                String finishDate = V.getFinishDayInsu();  //finishDate = Insurance end date

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = sdf.parse(timeStamp);
                Date date2 = sdf.parse(finishDate);

                if (date2.before(date1)) {
                    if (writeTo==1) {
                        System.out.print("\n\t\t\t\t "+"|"+V.getPlateNumber()+"|");
                    }else if (writeTo==2){
                        fileExport.appendToCsv("SortedPlateNumbers.csv", ","+V.getPlateNumber());
                    }
                }
            }
        }
        //************************DB*********************//
        else if(readFrom==2){
            Collections.sort(vehiclesInfDB, new Comparator<Vehicles>() {
                @Override
                public int compare(Vehicles o1, Vehicles o2) {
                    return o1.getPlateNumber().compareTo(o2.getPlateNumber());
                }
            });
            System.out.print("The car's plates registration sorting order selected is:\n\t\t\t  [Alpha-Numerical]");

            for (Vehicles V : vehiclesInfDB) {

                String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //timeStamp current date
                String finishDate = V.getFinishDayInsu();  //finishDate = Insurance end date

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = sdf.parse(timeStamp);
                Date date2 = sdf.parse(finishDate);

                if (date2.before(date1)) {
                    if (writeTo==1) {
                        System.out.print("\n\t\t\t\t "+"|"+V.getPlateNumber()+"|");
                    }
                    else if (writeTo==2){
                        fileExport.appendToCsv("SortedPlateNumbers.csv", ","+V.getPlateNumber());
                    }
                }
            }
        }
    }

    //TODO F4
    public void fineCalcByOwner(List<Vehicles> vehiclesInf,List<Vehicles> vehiclesInfDB, int readFrom, int writeTo, double fine) throws Exception {

        ExportFile fileExport = new ExportFile();
        if (writeTo==2){ fileExport.writeToCsv("fineCalcByOwner.csv", "No match found"); System.out.println("Csv will be generated");}

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //timeStamp h shmerini imerominia
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = sdf.parse(timeStamp);
        Map<String, Integer> fines = new HashMap<>();
        int i=1;

        //************************CsvParser*********************//
        if (readFrom==1) {
            for (Vehicles V : vehiclesInf) {

                String finishDate = V.getFinishDayInsu();  //x Η ΗΜΕΡΟΜΗΝΙΑ Π ΤΕΛΕΙΩΝΕΙ Η ΑΣΦΑΛΕΙΑ
                Date date2 = sdf.parse(finishDate);

                if (date1.after(date2)) {
                    if (fines.containsKey(V.getOwnerName())) {
                        // fines.put(V.getOwnerName(), i++);
                        // fines.put(fines, fines.get(Integer) + 1);
                        fines.computeIfPresent(V.getOwnerName(), (k, v) -> v + 1);
                    } else {
                        fines.put(V.getOwnerName(), i);
                    }
                }
            }
        }
        //************************DB*********************//
        else if(readFrom == 2) {

            for (Vehicles V : vehiclesInfDB) {

                String finishDate = V.getFinishDayInsu();  //x Η ΗΜΕΡΟΜΗΝΙΑ Π ΤΕΛΕΙΩΝΕΙ Η ΑΣΦΑΛΕΙΑ
                Date date2 = sdf.parse(finishDate);

                // test gia sigkrisi ktl
                if (date1.after(date2)) {
                    if (fines.containsKey(V.getOwnerName())) {
                        // fines.put(V.getOwnerName(), i++);
                        // fines.put(fines, fines.get(Integer) + 1);
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
            }
        }
    }
}

