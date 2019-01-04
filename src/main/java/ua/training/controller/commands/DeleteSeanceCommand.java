package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteSeanceCommand extends Command {
    private Logger logger = Logger.getLogger(DeleteSeanceCommand.class);
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        logger.info("DeleteSeanceCommand start");
        try (SeanceService seanceService = new SeanceService()) {
            int id = Integer.parseInt(request.getParameter("seance-id"));
            logger.info("Seance id: " + id);
            seanceService.deleteSeanceById(id);
            sendRedirect("free/home");
            logger.info("DeleteSeanceCommand end");
        }
    }
}
