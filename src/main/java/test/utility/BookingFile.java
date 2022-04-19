package test.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookingFile implements OutputFile{
    private ArrayList<String> content;

    @Override
    public void writeToFile(ArrayList<String> content) {
        this.content = content;
    }

    @Override
    public void save(Path path) throws IOException {
        FileWriter checkoutFile = new FileWriter(path.getParent().toString()+"/Output_"+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".csv");
        for(String line:content)
            checkoutFile.write(line+"\n");
        checkoutFile.close();
    }
}
