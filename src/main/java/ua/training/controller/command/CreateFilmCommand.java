package ua.training.controller.command;

import ua.training.model.entity.Film;
import ua.training.model.entity.User;
import ua.training.model.service.FilmService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

public class CreateFilmCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        String name = request.getParameter("name");
        String nameEN = request.getParameter("nameEN");
        String genre = request.getParameter("genre");
        String genreEn = request.getParameter("genreEN");
        String director = request.getParameter("director");
        String directorEN = request.getParameter("directorEN");
        float rate = Float.parseFloat(request.getParameter("rate"));
        String description = request.getParameter("description");
        String descriptionEN = request.getParameter("descriptionEN");
        Film film = new Film();
        film.setName(name);
        film.setNameEN(nameEN);
        film.setGenre(genre);
        film.setGenreEN(genreEn);
        film.setDirector(director);
        film.setDirectorEN(directorEN);
        film.setRate(rate);
        film.setDescription(description);
        film.setDescriptionEN(descriptionEN);
        FilmService filmService = new FilmService();
        try {
            filmService.createFilm(film);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role").equals(User.Role.ADMIN);
    }
}
