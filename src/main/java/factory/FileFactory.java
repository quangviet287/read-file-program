package factory;


import exception.TypeNotSupportedException;
import model.CSVFile;
import model.FileData;
import model.XMLFile;

import java.nio.file.Path;

public class FileFactory {

    public static FileData getFile(Path file) throws TypeNotSupportedException {
        String name = file.getFileName().toString();
        String[] fullFileName = name.split("\\.");
        String fileType = fullFileName[1];
        if ("csv".equals(fileType)) {
            return new CSVFile(file);
        } else if ("xml".equals(fileType)) {
            return new XMLFile(file);
        } else {
            throw new TypeNotSupportedException("Type not supported. Please try again!");
        }
    }

}
