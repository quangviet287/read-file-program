package service;

import exception.TypeNotSupportedException;
import factory.FileFactory;
import model.FileData;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class WatchServiceImpl {

    final static Logger logger = Logger.getLogger(WatchServiceImpl.class);

    public static void run(String directory){
        try {
            System.out.println("Watch service is started for directory: "+directory);
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

            logger.warn("Reimport successfully.");

        } catch (TypeNotSupportedException e) {
            logger.error(e.getErrMessage());
        }
//        catch (IOException e) {
//            logger.error(e.getMessage());
//        }
    }
}
