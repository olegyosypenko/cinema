package ua.training.controller.commands;

import ua.training.model.entity.Film;
import ua.training.model.service.FilmService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateSeancePageCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        FilmService filmService = new FilmService();
        List<Film> films = new ArrayList<>();
        try {
            films = filmService.getAllFilms();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("films", films);
        forward("/WEB-INF/pages/create-seance.jsp");
    }
}
