package ua.training.controller.commands;

import ua.training.model.dto.FilmDto;
import ua.training.model.service.FilmService;

import javax.servlet.http.HttpServletRequest;

public class CreateFilmCommand extends Command {
    @Override
    public void process(HttpServletRequest request) {
        String name = request.getParameter("name");
        String nameEN = request.getParameter("name-en");
        String genre = request.getParameter("genre");
        String genreEn = request.getParameter("genre-en");
        String director = request.getParameter("director");
        String directorEN = request.getParameter("director-en");
        float rate = Float.parseFloat(request.getParameter("rate"));
        String description = request.getParameter("description");
        String descriptionEN = request.getParameter("description-en");
        String imageLink = request.getParameter("image-link");
        String imageLinkEN = request.getParameter("image-link-en");
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
            sendRedirect("admin/create-film-page");
        }
    }
}
