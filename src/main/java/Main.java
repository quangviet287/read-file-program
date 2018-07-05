import exception.TypeNotSupportedException;
import factory.FileFactory;
import model.FileData;
import service.WatchServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws TypeNotSupportedException {
        Path file = Paths.get("E:\\Data\\companies.csv");
        FileData csvFile = FileFactory.getFile(file);
        csvFile.getNameOfCompanyByCountry(file);
        csvFile.getTotalCapitalByCountry(file);

        WatchServiceImpl watchService = new WatchServiceImpl();
    }
}
