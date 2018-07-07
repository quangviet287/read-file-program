package service;

import exception.TypeNotSupportedException;
import factory.FileFactory;
import model.Company;
import model.FileData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static service.CSVFileServiceImpl.getNameOfCompanyByCountry;
import static service.CSVFileServiceImpl.getTotalCapitalByCountry;

public class WatchServiceImpl {

    @Autowired
    CSVFileServiceImpl csvFileService;

    final static Logger logger = Logger.getLogger(WatchServiceImpl.class);

    public static void run(String directory){
        try {
            logger.info("Watch service is started for directory: "+directory);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(directory);
            WatchKey key = dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            for (;;) {

                try {
                    key = watchService.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }

                    WatchEvent<Path> ev = (WatchEvent<Path>)event;
                    Path fileName = ev.context();

                    Path child = dir.resolve(fileName);
                    if (!Files.probeContentType(child).equals("application/vnd.ms-excel") // CSV file
                            && !Files.probeContentType(child).equals("text/plain") // Text file
                            && !Files.probeContentType(child).equals("text/xml")) { //XML file
                        logger.warn("File is '"+fileName+"' not a valid file.");
                        continue;
                    }

                    logger.info("File '"+fileName+"' is modified.");
                    Path file = Paths.get(directory + fileName);
                    readFileData(file);

                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private static void readFileData(Path file) {
        try {
            FileData fileData = FileFactory.getFile(file);
            List<Company> companyList = fileData.getDataContent(file);
            logger.warn("Reimport successfully. New content such as: ");
            companyList.stream().forEach(e->logger.info(e));
            getNameOfCompanyByCountry(companyList);
            getTotalCapitalByCountry(companyList);

        } catch (TypeNotSupportedException e) {
            logger.error(e.getErrMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
