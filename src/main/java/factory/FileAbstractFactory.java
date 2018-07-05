package factory;

import model.File;

import java.nio.file.Path;
import java.util.List;

public interface FileAbstractFactory {

    List<?> getContentFile(Path file);

//    List<?> getListElementsByCountry(Path file, String country);
//
//    int getTotalCapitalByCountry(Path file, String country);
}
