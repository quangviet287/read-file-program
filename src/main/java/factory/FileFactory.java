package factory;

import java.nio.file.Path;
import java.util.List;

public class FileFactory {

    public static List<?> getFile(FileAbstractFactory fileAbstractFactory, Path file){
        return fileAbstractFactory.getContentFile(file);
    }

}
