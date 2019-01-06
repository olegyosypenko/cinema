package ua.training.controller.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CalendarUtil {
    public static List<Date> getAvailableDays() {
        List<Date> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(new Date(System.currentTimeMillis() + i * 24 * 60 * 60 * 1000));
        }
        return list;
    }
    public static boolean isDateAvailable(Date date) {
        for (Date day : getAvailableDays()) {
            if (date.toString().equals(day.toString())) {
                return true;
            }
        }
        return false;
    }

    public static Timestamp getTimestampFromString(String input) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            Timestamp startTime = new Timestamp(formatter.parse(input).getTime());
            return startTime;
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    public static Date getDateFromString(String input) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = new Date(formatter.parse(input).getTime());
        } catch (Exception e) {
            date = new Date(System.currentTimeMillis());
        }
        return date;

    }

}
