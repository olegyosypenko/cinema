package ua.training.controller.commands;

import ua.training.model.service.FilmService;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand extends Command {
    private FilmService filmService = new FilmService();
    @Override
    public String process(HttpServletRequest request) {
        request.setAttribute("films", filmService.getMostPopularFilms());
        return "/index.jsp";
    }
}
