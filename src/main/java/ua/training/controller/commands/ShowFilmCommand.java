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
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        int filmId = UriParser.getIndexFromUri(request.getRequestURI());
        try (FilmService filmService = new FilmService();
             SeanceService seanceService = new SeanceService()) {
            logger.trace("process start");
            Film film = filmService.getFilmById(filmId);
            film.setSeances(seanceService.getSeancesByFilmId(filmId));
            logger.debug("Number of seances: " + film.getSeances());
            request.setAttribute("film", film);
            request.setAttribute("seances", film.getSeances());
            forward("/WEB-INF/pages/film.jsp");
        }
    }
}
