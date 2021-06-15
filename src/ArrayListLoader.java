import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListLoader {

    private ArrayList<String> dataArray;
    private String fileName;

    //Loads data from the file specified in 'fileName' into an ArrayList.
    public ArrayListLoader(String fileName){
        setFileName(fileName);
        dataArray = new ArrayList<String>();
    }

    public void load(){
        try {

            File inputFile = new File(this.fileName);
            Scanner fileScanner = new Scanner(inputFile);

            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                dataArray.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found, try again duder...");
        }
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public ArrayList<String> getDataArray(){
        return this.dataArray;
    }
}
