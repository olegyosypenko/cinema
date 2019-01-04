package ua.training.controller.commands;

import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class ScheduleCommand extends Command {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        try (SeanceService seanceService = new SeanceService()) {
            request.setAttribute("seances", seanceService.getSeancesByDate(new Date(System.currentTimeMillis())));
            forward("/WEB-INF/pages/schedule.jsp");
        }
    }
}