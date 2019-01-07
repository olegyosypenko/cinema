package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.UriParser;
import ua.training.model.service.FilmService;
import ua.training.model.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteFilmCommand extends Command {
    private Logger logger = Logger.getLogger(DeleteFilmCommand.class);
    private FilmService filmService = new FilmService();
    @Override
    public String process(HttpServletRequest request) {
        int id = UriParser.getIndexFromUri(request.getRequestURI());
        try {
            filmService.deleteFilmById(id);
            return "redirect:free/films/1?success=film-deleted";
        } catch (ServiceException e) {
            logger.error("Cannot delete film", e);
            return "redirect:free/film/" + id + "?error=cannot-delete-film";
        }
    }
}
