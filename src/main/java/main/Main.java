package main;

import org.apache.log4j.Logger;
import service.WatchService;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args){
           if (args.length == 0){
               LOGGER.error("No argument found. Please add program arguments before you run project.");
               System.exit(-1);
           }
           String directory = args[0];
           WatchService watchService = new WatchService();
           watchService.run(directory);
    }
}
