package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteSeanceCommand extends Command {
    private Logger logger = Logger.getLogger(DeleteSeanceCommand.class);
    private SeanceService seanceService = new SeanceService();
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        logger.info("DeleteSeanceCommand start");
        int id = Integer.parseInt(request.getParameter("seance-id"));
        logger.info("Seance id: " + id);
        seanceService.deleteSeanceById(id);
        logger.info("DeleteSeanceCommand end");
        return "redirect:free/home?success=seance-deleted";
    }

}
