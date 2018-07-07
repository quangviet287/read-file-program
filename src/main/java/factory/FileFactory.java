package factory;


import exception.TypeNotSupportedException;
import model.CSVFile;
import model.FileData;

import java.nio.file.Path;

public class FileFactory {

    public static FileData getFile(Path file) throws TypeNotSupportedException {
        String fileName = file.getFileName().toString();
        if (fileName.endsWith(".csv")) {
            return new CSVFile(fileName);
        } else {
            throw new TypeNotSupportedException("Type not supported. Please try again!");
        }
    }

}
