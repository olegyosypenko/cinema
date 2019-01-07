package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.FilmDto;
import ua.training.model.service.FilmService;

import javax.servlet.http.HttpServletRequest;

public class CreateFilmCommand extends Command {
    Logger logger = Logger.getLogger(CreateFilmCommand.class);
    private FilmService filmService = new FilmService();

    @Override
    public String process(HttpServletRequest request) {
        if (!isCorrectInput(request)) {
            return "redirect:admin/create-film-page?error=incorrect-input";
        }
        try {
            filmService.createFilm(getFilmDtoFromInput(request));
            return "redirect:admin/create-film-page?success=film-created";
        } catch (NotUniqueValueException e) {
            return "redirect:admin/create-film-page?error=not-unique-film-value";
        }
    }

    private boolean isCorrectInput(HttpServletRequest request) {
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
        String rateParam = request.getParameter("rate");
        if (name == null || nameEN == null || genre == null || genreEn == null
                || director == null || directorEN == null || description == null
                || descriptionEN == null || imageLink == null || imageLinkEN == null ||
                rateParam == null) {
            return false;
        }
        if (name.length() > Constants.NAME_MAX_LENGTH || nameEN.length() > Constants.NAME_MAX_LENGTH ||
                genre.length() > Constants.GENRE_MAX_LENGTH || genreEn.length() > Constants.GENRE_MAX_LENGTH ||
                director.length() > Constants.DIRECTOR_MAX_LENGTH || directorEN.length() > Constants.DIRECTOR_MAX_LENGTH ||
                imageLink.length() > Constants.LINK_MAX_LENGTH || imageLinkEN.length() > Constants.LINK_MAX_LENGTH ||
                description.length() > Constants.DESCRIPTION_MAX_LENGTH ||
                descriptionEN.length() > Constants.DESCRIPTION_MAX_LENGTH) {
            return false;
        }
        try {
            float rate = Float.parseFloat(rateParam);
            if (rate > Constants.MAX_RATE || rate < Constants.MIN_RATE) {
                return false;
            }
        } catch (NumberFormatException e) {
            logger.error("Incorrect rate input", e);
            return false;
        }
        return true;
    }

    private FilmDto getFilmDtoFromInput(HttpServletRequest request) {
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
        float rate = Float.parseFloat(request.getParameter("rate"));
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
        return film;
    }
}
