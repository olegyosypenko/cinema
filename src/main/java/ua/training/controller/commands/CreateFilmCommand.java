package ua.training.controller.commands;

import ua.training.model.dto.FilmDto;
import ua.training.model.entity.User;
import ua.training.model.service.FilmService;

import java.io.IOException;
import java.sql.SQLException;

public class CreateFilmCommand extends Command {
    @Override
    public void process() throws IOException {
        String name = request.getParameter("name");
        String nameEN = request.getParameter("nameEN");
        String genre = request.getParameter("genre");
        String genreEn = request.getParameter("genreEN");
        String director = request.getParameter("director");
        String directorEN = request.getParameter("directorEN");
        float rate = Float.parseFloat(request.getParameter("rate"));
        String description = request.getParameter("description");
        String descriptionEN = request.getParameter("descriptionEN");
        String imageLink = request.getParameter("image-link");
        String imageLinkEN = request.getParameter("image-linkEN");
        FilmDto film = new FilmDto();
        film.setName(name);
        film.setNameEN(nameEN);
        film.setGenre(genre);
        film.setGenreEN(genreEn);
        film.setDirector(director);
        film.setDirectorEN(directorEN);
        film.setRate(rate);
        film.setDescription(description);
        film.setDescriptionEN(descriptionEN);
        film.setImageLink(imageLink);
        film.setImageLinkEN(imageLinkEN);
        FilmService filmService = new FilmService();
        try {
            filmService.createFilm(film);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sendRedirect("home");
    }

    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role").equals(User.Role.ADMIN);
    }
}
