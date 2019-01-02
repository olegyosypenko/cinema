package ua.training.controller.commands;

import ua.training.model.entity.Film;
import ua.training.model.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateSeancePageCommand extends Command {
    @Override
    public void process(HttpServletRequest request) {
        try (FilmService filmService = new FilmService()) {
            List<Film> films = filmService.getAllFilms();
            request.setAttribute("films", films);
            forward("/WEB-INF/pages/create-seance.jsp");
        }
    }
}
