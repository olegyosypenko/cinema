package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.CalendarUtil;
import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class ShowSeancesByDateCommand extends Command {
    private static final Logger logger = Logger.getLogger(ShowSeancesByDateCommand.class);
    private SeanceService seanceService = new SeanceService();
    @Override
    public String process(HttpServletRequest request) {
        String[] requestParts = request.getRequestURI().split("/");
        String dateString = requestParts[requestParts.length - 1];
        Date date = CalendarUtil.getDateFromString(dateString);
        logger.debug("Date: " + date);
        if (!CalendarUtil.isDateAvailable(date)) {
            date = new Date(System.currentTimeMillis());
        }
        request.setAttribute("days", CalendarUtil.getAvailableDays());
        request.setAttribute("date", date);
        request.setAttribute("seances", seanceService.getSeancesByDate(date));
        return "/WEB-INF/pages/schedule.jsp";
    }

}

