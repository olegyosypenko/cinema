package ua.training.controller.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalendarUtil {
    private static final int DAY = 24 * 60 * 60 * 1000;
    private static final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm";
    private static final String DAY_FORMAT = "yyyy-MM-dd";

    public static List<Date> getAvailableDays() {
        return IntStream.range(0, 6)
                .mapToObj(dayNumber -> new Date(System.currentTimeMillis() + dayNumber * DAY))
                .collect(Collectors.toList());
    }

    public static boolean isDateAvailable(Date date) {
        return getAvailableDays()
                .stream()
                .map(Date::toString)
                .anyMatch((availableDate -> availableDate.equals(date.toString())));
    }

    public static Timestamp getTimestampFromString(String input) {
        DateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
        try {
            return new Timestamp(formatter.parse(input).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Could not parse input: " + input, e);
        }
    }

    public static Date getDateFromString(String input) {
        DateFormat formatter = new SimpleDateFormat(DAY_FORMAT);
        Date date;
        try {
            date = new Date(formatter.parse(input).getTime());
        } catch (Exception e) {
            date = new Date(System.currentTimeMillis());
        }
        return date;
    }

}
