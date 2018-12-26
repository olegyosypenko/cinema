package ua.training.controller.commands;

import ua.training.model.service.SeanceService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Date;

public class ScheduleCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        SeanceService seanceService = new SeanceService();
        request.setAttribute("seances", seanceService.getSeancesByDate(new Date(System.currentTimeMillis())));
        forward("/WEB-INF/pages/schedule.jsp");
    }
}

