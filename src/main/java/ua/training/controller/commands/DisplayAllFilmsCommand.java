package ua.training.controller.commands;

import ua.training.model.service.FilmService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

public class DisplayAllFilmsCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        FilmService filmService = new FilmService();
        request.setAttribute("films", filmService.getAllFilms());

        forward("/WEB-INF/pages/display-all-films.jsp");
    }
}
