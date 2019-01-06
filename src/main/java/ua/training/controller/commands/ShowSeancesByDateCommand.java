package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.CalendarUtil;
import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ShowSeancesByDateCommand extends Command {
    private static final Logger logger = Logger.getLogger(ShowSeancesByDateCommand.class);
    private SeanceService seanceService = new SeanceService();
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String[] requestParts = request.getRequestURI().split("/");
        String dateString = requestParts[requestParts.length - 1];
        Date date = CalendarUtil.getDateFromString(dateString);
        if (!CalendarUtil.isDateAvailable(date)) {
            date = new Date(System.currentTimeMillis());
        }
        request.setAttribute("days", CalendarUtil.getAvailableDays());
        request.setAttribute("date", date);
        request.setAttribute("seances", seanceService.getSeancesByDate(date));
        return "/WEB-INF/pages/schedule.jsp";
    }

}

