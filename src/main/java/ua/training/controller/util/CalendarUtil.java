package ua.training.controller.util;

import java.sql.Date;
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

}
