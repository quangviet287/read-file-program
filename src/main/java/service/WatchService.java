package service;

import exception.TypeNotSupportedException;
import factory.FileFactory;
import model.Company;
import factory.FileData;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;


public class WatchService {

    private static final Logger LOGGER = Logger.getLogger(WatchService.class);

    private static final String[] TYPE_SUPPORTED = new String[]{"application/vnd.ms-excel", "text/plain", "text/xml"};

    public void run(String directory) throws IllegalArgumentException {
        if (checkDirectory(directory)) {
            try {
                LOGGER.info("Watch service is started for directory: " + directory);
                java.nio.file.WatchService watchService = FileSystems.getDefault().newWatchService();
                Path dir = Paths.get(directory);
                WatchKey key = dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                while (key.reset()) {

                    key = watchService.take();

                    for (WatchEvent event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind == StandardWatchEventKinds.OVERFLOW) {
                            continue;
                        }

                        Path fileName = (Path) event.context();

                        Path child = dir.resolve(fileName);
                        if (!ArrayUtils.contains(TYPE_SUPPORTED, Files.probeContentType(child))) {
                            LOGGER.warn("File '" + fileName + "' is not a valid file.");
                            continue;
                        }

                        LOGGER.info("File '" + fileName + "' is modified.");
                        Path file = Paths.get(directory + fileName);
                        readFileData(file);

                    }
                }
                watchService.close();
            } catch (IOException | InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            LOGGER.error("Directory is invalid");
            throw new IllegalArgumentException("Directory is invalid");
        }
    }

    private void readFileData(Path file) {
        try {
            FileData fileData = FileFactory.getFile(file);
            List<Company> companyList = fileData.getDataContent();
            LOGGER.warn("Reimport successfully.");
            ReadFileService readFileService = new ReadFileService();
            readFileService.showNameOfCompanyByCountry(companyList);
            readFileService.showTotalCapitalByCountry(companyList);

        } catch (TypeNotSupportedException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error("Error when reading file", e);

        }
    }

    private boolean checkDirectory(String directory) {
        File file = new File(directory);
        return file.isDirectory();
    }
}
