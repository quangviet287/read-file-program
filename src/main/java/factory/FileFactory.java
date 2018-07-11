package factory;


import exception.TypeNotSupportedException;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Path;

public class FileFactory {

    private static final String CSV_FILE = "csv";

    public static FileData getFile(Path file) throws TypeNotSupportedException {
        String fileName = file.getFileName().toString();
        if (FilenameUtils.isExtension(fileName, CSV_FILE)) {
            return new CSVFile(file.toString());
        }
        throw new TypeNotSupportedException(file.getFileName().toString());

    }

}
