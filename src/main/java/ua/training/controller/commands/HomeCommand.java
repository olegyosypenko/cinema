package ua.training.controller.commands;

import ua.training.model.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommand extends Command {
    FilmService filmService = new FilmService();
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("films", filmService.getMostPopularFilms());
        return "/index.jsp";
    }
}
