package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UniqueNamingUtil {

    private UniqueNamingUtil(){}

    public static String getUniqueName(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");

        return LocalDateTime.now().format(formatter);
    }
}
