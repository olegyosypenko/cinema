package ua.training.controller.commands;

import ua.training.model.service.SeanceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;

public class ScheduleCommand extends Command {
    @Override
    public void process(HttpServletRequest request) {
        try (SeanceService seanceService = new SeanceService()) {
            request.setAttribute("seances", seanceService.getSeancesByDate(new Date(System.currentTimeMillis())));
            forward("/WEB-INF/pages/schedule.jsp");
        }
    }
}