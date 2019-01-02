package ua.training.controller.commands;

import ua.training.model.service.FilmService;

import javax.servlet.http.HttpServletRequest;

public class DisplayAllFilmsCommand extends Command {
    @Override
    public void process(HttpServletRequest request) {
        try (FilmService filmService = new FilmService()) {
            request.setAttribute("films", filmService.getAllFilms());

            forward("/WEB-INF/pages/display-all-films.jsp");
        }
    }
}
