package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.UriParser;
import ua.training.model.service.FilmService;
import ua.training.model.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFilmCommand extends Command {
    private Logger logger = Logger.getLogger(DeleteFilmCommand.class);
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        int id = UriParser.getIndexFromUri(request.getRequestURI());
        try (FilmService filmService = new FilmService()) {
            filmService.deleteFilmById(id);
            sendRedirect("free/films/1");
        } catch (ServiceException e) {
            logger.error("Cannot delete film", e);
            sendRedirect("free/film/" + id );
        }
    }
}
