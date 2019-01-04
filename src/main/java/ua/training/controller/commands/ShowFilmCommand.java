package ua.training.controller.commands;

import ua.training.controller.util.UriParser;
import ua.training.model.entity.Film;
import ua.training.model.service.FilmService;
import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;

public class ShowFilmCommand extends Command {
    @Override
    public void process(HttpServletRequest request) {
        int filmId = UriParser.getIndexFromUri(request.getRequestURI());
        try (FilmService filmService = new FilmService();
             SeanceService seanceService = new SeanceService()) {
            Film film = filmService.getFilmById(filmId);
            film.setSeances(seanceService.getSeancesByFilmId(filmId));
            request.setAttribute("film", film);
            request.setAttribute("seances", film.getSeances());
            forward("/WEB-INF/pages/film.jsp");
        }
    }
}
