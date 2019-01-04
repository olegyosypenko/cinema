package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.dto.FilmDto;
import ua.training.model.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateFilmCommand extends Command {
    Logger logger = Logger.getLogger(CreateFilmCommand.class);
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String nameEN = request.getParameter("name-en");
        String genre = request.getParameter("genre");
        String genreEn = request.getParameter("genre-en");
        String director = request.getParameter("director");
        String directorEN = request.getParameter("director-en");
        String description = request.getParameter("description");
        String descriptionEN = request.getParameter("description-en");
        String imageLink = request.getParameter("image-link");
        String imageLinkEN = request.getParameter("image-link-en");
        if (name == null || nameEN == null || genre == null || genreEn == null
                || director == null || directorEN == null || description == null
                || descriptionEN == null || imageLink == null || imageLinkEN == null ||
                request.getParameter("rate") == null) {
            sendRedirect("admin/create-film-page?error=1");
            return;
        }
        float rate = 0.0f;
        try {
            rate = Float.parseFloat(request.getParameter("rate"));
        } catch (NumberFormatException e) {
            logger.error("Incorrect rate input", e);
            sendRedirect("admin/create-film-page?error=2");
            return;
        }

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
        try (FilmService filmService = new FilmService()) {
            filmService.createFilm(film);
            sendRedirect("admin/create-film-page?error-code=1");
        }
    }
}
