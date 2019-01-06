package ua.training.controller.commands;

import ua.training.model.entity.Film;
import ua.training.model.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateSeancePageCommand extends Command {
    FilmService filmService = new FilmService();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        List<Film> films = filmService.getAllFilms();
        request.setAttribute("films", films);
        forward("/WEB-INF/pages/create-seance.jsp");
    }
}
