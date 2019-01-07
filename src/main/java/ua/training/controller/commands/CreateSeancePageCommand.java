package ua.training.controller.commands;

import ua.training.model.entity.Film;
import ua.training.model.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateSeancePageCommand extends Command {
    private FilmService filmService = new FilmService();
    @Override
    public String process(HttpServletRequest request) {
        List<Film> films = filmService.getAllFilms();
        request.setAttribute("films", films);
        return "/WEB-INF/pages/create-seance.jsp";
    }
}
