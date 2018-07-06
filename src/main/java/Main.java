import exception.TypeNotSupportedException;
import factory.FileFactory;
import model.FileData;
import service.CSVFileServiceImpl;
import service.WatchServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
           if (args.length == 0){
               System.err.println("No argument found.");
               System.exit(-1);
           }
           String directory = args[0];
           WatchServiceImpl watchService = new WatchServiceImpl(directory);
    }
}
