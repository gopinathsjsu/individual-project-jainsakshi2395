package test.utility;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public interface OutputFile {
    void writeToFile(ArrayList<String> content);
    void save(Path path) throws IOException;
}
