import java.io.FileWriter;



public class ExportFile {



    //create a file

    public void writeToCsv(String fileName, String text) throws Exception {

        FileWriter writer = new FileWriter(fileName);

        writer.write(text.replaceAll("\\[", "").replaceAll("\\]",""));

        writer.close();

    }



    //add extra text

    public void appendToCsv(String fileName, String text) throws Exception {

        FileWriter writer = new FileWriter(fileName, true);

        writer.write(""+text.replaceAll("\\[", "").replaceAll("\\]",""));

        writer.close();

    }}