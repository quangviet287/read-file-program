package service;

import exception.TypeNotSupportedException;
import factory.FileFactory;
import model.FileData;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class WatchServiceImpl {

    public WatchServiceImpl(String directory){
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
                    if (!Files.probeContentType(child).equals("application/vnd.ms-excel")) {
                        System.err.println("File '" + fileName + "' is not a CSV file.");
                        continue;
                    }

                    System.out.println("File '" + fileName + "' is modified.");
                    Path file = Paths.get(directory + fileName);
                    readFileData(file);

                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void readFileData(Path file) {
        try {
            FileData fileData = FileFactory.getFile(file);
            System.out.println("Reimport successfully. New content such as : ");
            List<?> listContent = fileData.getDataContent(file);
            listContent.stream().forEach(e-> System.out.println(e));
            CSVFileServiceImpl csvFileService = new CSVFileServiceImpl(file);
        } catch (TypeNotSupportedException e) {
            System.err.println(e.getErrMessage());
        } catch (IOException e) {
            System.err.println("File not found. "+ e.getMessage());
        }
    }
}
