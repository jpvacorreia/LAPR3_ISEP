package lapr.project.data.api;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileConnectionWithC {


    //Hiding the implicit public one
    private FileConnectionWithC(){

    }

    public static double getDataFromFile(Date date) throws IOException {
        SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String fileNameTrigger = "estimate_"+sdt.format(date)+".data.flag";
        String fileNameWithInfo = "C_Assembly\\estimate_"+sdt.format(date)+".data";
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path directory = Paths.get("C_Assembly");
        WatchKey watchKey = directory.register(watchService,StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_MODIFY);
        while (true){
            for (WatchEvent<?> event : watchKey.pollEvents()){
                Path file =(Path) event.context();
                if (file.getFileName().toString().equalsIgnoreCase(fileNameTrigger)){
                    try {
                        Scanner fileOutput = new Scanner(new File(fileNameWithInfo));
                        String cTime = fileOutput.next();
                        fileOutput.close();
                        File newFile = new File(file.getFileName().toString());
                        newFile.delete();
                        Path fileWithInfo = Paths.get(fileNameWithInfo);
                        File newFile2 = new File(fileWithInfo.getFileName().toString());
                        newFile2.delete();
                        return Double.parseDouble(cTime);
                    } catch (Exception e){
                        return -1;
                    }
                }
            }
        }
    }

    public static boolean setFile(Date data, double batteryCapacity, double stateOfCharge, double parkingOutputInWatts) throws IOException {
        List<String> lines = Arrays.asList(String.valueOf((int)batteryCapacity),String.valueOf((int)stateOfCharge),String.valueOf((int)parkingOutputInWatts));
        SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String fileName = "C_Assembly\\lock_"+sdt.format(data)+".data";
        Path file = Paths.get(fileName);
        Files.write(file,lines, StandardCharsets.UTF_8);
        return true;
    }
}
