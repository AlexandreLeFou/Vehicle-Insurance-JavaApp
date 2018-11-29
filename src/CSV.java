import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CSV {

    // this method will read data from the CSV file and return as a list

    public static List<String[]> read(String file) {   //where file is the path of csv

        LinkedList<String[]> data = new LinkedList<String[]>();
        String dataRow;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((dataRow = br.readLine()) != null){       //reads only one line
                String[] dataRecords = dataRow.split(",");
                data.add(dataRecords);
            }
        } catch (IOException e){

            e.printStackTrace();
        }
        return data;

    }
}

