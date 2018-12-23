package ua.training.controller.commands;

import ua.training.model.service.FilmService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

public class DisplayAllFilmsCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        FilmService filmService = new FilmService();
        try {
            request.setAttribute("films", filmService.getAllFilms());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        forward("/WEB-INF/pages/display-all-films.jsp");
    }

    @Override
    public boolean isAccessAllowed() {
        return true;
    }
}
