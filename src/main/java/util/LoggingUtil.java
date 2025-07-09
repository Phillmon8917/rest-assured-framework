package util;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingUtil {
    public static void log(String className, String methodName, String exception){

        String fileName = "Log" + UniqueNamingUtil.getUniqueName();
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
