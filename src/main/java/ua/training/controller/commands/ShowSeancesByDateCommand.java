package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ShowSeancesByDateCommand extends Command {
    private static final Logger logger = Logger.getLogger(ShowSeancesByDateCommand.class);
    @Override
    public void process(HttpServletRequest request) {
        Date date;
        String[] requestParts = request.getRequestURI().split("/");
        String dateString = requestParts[requestParts.length - 1];
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = new Date(formatter.parse(dateString).getTime());
        } catch (Exception e) {
            date = new Date(System.currentTimeMillis());
            logger.error("Incorrect input error: " + dateString, e);
        }
        request.setAttribute("days", getAvailableDays());
        try (SeanceService seanceService = new SeanceService()) {
            request.setAttribute("seances", seanceService.getSeancesByDate(date));
            forward("/WEB-INF/pages/schedule.jsp");
        }
    }

    public List<Date> getAvailableDays() {
        List<Date> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(new Date(System.currentTimeMillis() + i * 24 * 60 * 60 * 1000));
        }
        return list;
    }
}

