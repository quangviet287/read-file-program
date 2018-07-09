package service;

import exception.TypeNotSupportedException;
import factory.FileFactory;
import model.Company;
import model.FileData;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;


public class WatchService {

    final static Logger logger = Logger.getLogger(WatchService.class);

    static String [] TYPE_SUPPORTED = new String[] {"application/vnd.ms-excel","text/plain","text/xml"};

    public void run(String directory){
        try {
            logger.info("Watch service is started for directory: "+directory);
            java.nio.file.WatchService watchService = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(directory);
            WatchKey key = dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            for (;;) {

                try {
                    key = watchService.take();
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }

                    WatchEvent<Path> ev = (WatchEvent<Path>)event;
                    Path fileName = ev.context();

                    Path child = dir.resolve(fileName);

                    if (TYPE_SUPPORTED.equals(Files.probeContentType(child))){
                        logger.warn("File is '"+fileName+"' not a valid file.");
                        continue;
                    }
                    /*if (!Files.probeContentType(child).equals("application/vnd.ms-excel") // CSV file
                            && !Files.probeContentType(child).equals("text/plain") // Text file
                            && !Files.probeContentType(child).equals("text/xml")) { //XML file
                        logger.warn("File is '"+fileName+"' not a valid file.");
                        continue;
                    }*/

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

    private void readFileData(Path file) {
        try {
            FileData fileData = FileFactory.getFile(file);
            List<Company> companyList = fileData.getDataContent(file);
            logger.warn("Reimport successfully. New content such as: ");
            companyList.stream().forEach(e->logger.info(e));
            CSVFileService csvFileService = new CSVFileService();
            csvFileService.getNameOfCompanyByCountry(companyList);
            csvFileService.getTotalCapitalByCountry(companyList);

        } catch (TypeNotSupportedException e) {
            logger.error(e.getErrMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
