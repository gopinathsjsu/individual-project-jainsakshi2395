package test.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileUtility {

    Path filePath;
    OutputFile outputFile;

    private ArrayList<String> contentFile = new ArrayList<>();

    public FileUtility(String pathToFile) {
        this.filePath = Paths.get(pathToFile);
    }

    public void fileReader(boolean firstLine) throws Exception{
        if(Files.exists(filePath)){
            BufferedReader reader = new BufferedReader((new FileReader(filePath.toFile())));
            String line = "";

            while((line=reader.readLine())!=null){
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                contentFile.add(line);
            }
        }else{
            throw new Exception();
        }
    }
    public ArrayList<String> getContentFile() {
        return contentFile;
    }

    public void writeOutput(ArrayList<String> message, boolean isError) throws IOException {
        if(isError){
            outputFile = new ErrorFile();
        }else{
            outputFile = new BookingFile();
        }
        outputFile.writeToFile(message);
        outputFile.save(filePath);
    }

}
