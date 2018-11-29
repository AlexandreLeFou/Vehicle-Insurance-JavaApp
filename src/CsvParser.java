import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CsvParser { // this method will read data from the Csv file and return as a list

     /**
     * @param file: contains the string value of the (csv) file to be parsed e.g.example.csv
     */
    public static List<String[]> read(String file) {   //according to csv's file path we provide the input csv string

        LinkedList<String[]> data = new LinkedList<String[]>();
        String dataRow;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((dataRow = br.readLine()) != null) {       //reads only one line
                String[] dataRecords = dataRow.split(","); //splits according to comma since we have comma seperated values
                data.add(dataRecords); //adds to a list the column i.e. comma value
             }
        } catch (IOException e) { e.printStackTrace(); }
        return data;   }}
