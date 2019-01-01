package ua.training.controller.commands;

import ua.training.controller.util.UriParser;
import ua.training.model.entity.Film;
import ua.training.model.service.FilmService;

public class ShowFilmCommand extends Command {
    @Override
    public void process() {
        int filmId = UriParser.getIndexFromUri(request.getRequestURI());
        FilmService filmService = new FilmService();
        Film film = filmService.getFilmById(filmId);
        request.setAttribute("film", film);
        forward("/WEB-INF/pages/film.jsp");
    }
}
