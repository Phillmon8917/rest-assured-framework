package util;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.*;

public class FileAttachmentUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");

    public static List<File> getLatestFiles(String directoryPath, int numberOfFiles) {
        File dir = new File(directoryPath);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Provided path is not a directory: " + directoryPath);
        }

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }

        Pattern datePattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}_\\d{6})");

        List<FileWithDate> fileDates = new ArrayList<>();
        for (File file : files) {
            Matcher matcher = datePattern.matcher(file.getName());
            if (matcher.find()) {
                String dateStr = matcher.group(1);
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
                    fileDates.add(new FileWithDate(file, dateTime));
                } catch (Exception ex) {
                    LoggingUtil.log("EmailSender", "getLatestFiles", ex.toString());
                }
            }
        }

        fileDates.sort((f1, f2) -> f2.dateTime.compareTo(f1.dateTime));

        List<File> latestFiles = new ArrayList<>();
        for (int i = 0; i < Math.min(numberOfFiles, fileDates.size()); i++) {
            latestFiles.add(fileDates.get(i).file);
        }

        return latestFiles;
    }

    private static class FileWithDate {
        File file;
        LocalDateTime dateTime;

        FileWithDate(File file, LocalDateTime dateTime) {
            this.file = file;
            this.dateTime = dateTime;
        }
    }
}