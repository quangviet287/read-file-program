import service.WatchServiceImpl;

public class Main {
    public static void main(String[] args){
           if (args.length == 0){
               System.err.println("No argument found.");
               System.exit(-1);
           }
           String directory = args[0];
           WatchServiceImpl.run(directory);
    }
}
