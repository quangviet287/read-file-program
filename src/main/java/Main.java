import org.apache.log4j.Logger;
import service.WatchServiceImpl;

public class Main {
    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args){
           if (args.length == 0){
               logger.error("No argument found.");
               System.exit(-1);
           }
           String directory = args[0];
           WatchServiceImpl.run(directory);
    }
}
