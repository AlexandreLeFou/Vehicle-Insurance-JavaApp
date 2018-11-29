import java.io.FileWriter;

public class ExportFile {
    /**
     * @param fileName:  contains the file name (string) of the file to be processed
     * @param text:  contains the text that will be written or appended to a file
     */

    public void writeToCsv(String fileName, String text) throws Exception { //write to a CSV file

        FileWriter writer = new FileWriter(fileName);
        writer.write(text.replaceAll("\\[", "").replaceAll("\\]",""));
        writer.close();  }

       public void appendToCsv(String fileName, String text) throws Exception {//append to a CSV file

        FileWriter writer = new FileWriter(fileName, true);
        writer.write(""+text.replaceAll("\\[", "").replaceAll("\\]",""));
        writer.close(); }}