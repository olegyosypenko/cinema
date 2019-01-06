package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.UriParser;
import ua.training.model.entity.Film;
import ua.training.model.service.FilmService;
import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowFilmCommand extends Command {
    private Logger logger = Logger.getLogger(ShowFilmCommand.class);
    private FilmService filmService = new FilmService();
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        int filmId = UriParser.getIndexFromUri(request.getRequestURI());
        logger.trace("process start");
        Film film = filmService.getFilmById(filmId); // ToDo refactor into one service instead of two!!!! done
        logger.debug("Number of seances: " + film.getSeances());
        request.setAttribute("film", film);
        request.setAttribute("seances", film.getSeances());
        return "/WEB-INF/pages/film.jsp";
    }
}
