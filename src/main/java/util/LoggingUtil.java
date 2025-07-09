package util;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingUtil {
    public static void log(String className, String methodName, String exception){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
        String fileName = "Log" + LocalDateTime.now().format(formatter);
        try {
            File file = new File("src/main/java/logs/" + fileName + ".txt");

            if(!file.exists())
            {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write("Exception details: \n\n");
                writer.write("Class Name: " + className + "\n");
                writer.write("Method name: " + methodName + "\n");
                writer.write("Exception: " + exception);
                writer.close();
            }

        }catch (Exception ex){
            LoggingUtil.log("LoggingUtil", "log", ex.toString());
        }
    }
}
