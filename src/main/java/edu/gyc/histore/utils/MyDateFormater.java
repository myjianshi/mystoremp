package edu.gyc.histore.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyDateFormater {
    public static String killT(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String newTime = dateTimeFormatter.format(localDateTime);

        return newTime;
    }
}
