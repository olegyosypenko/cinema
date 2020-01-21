package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.RegexUtil;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.FilmDto;
import ua.training.model.service.FilmService;

import javax.servlet.http.HttpServletRequest;

public class CreateFilmCommand extends Command {
    Logger logger = Logger.getLogger(CreateFilmCommand.class);
    private FilmService filmService = new FilmService();

    @Override
    public String process(HttpServletRequest request) {
        FilmDto filmDto = getFilmDtoFromInput(request);
        if (!isValidFilmDto(filmDto)) {
            return "redirect:admin/create-film-page?error=incorrect-input";
        }
        try {
            filmService.createFilm(filmDto);
            return "redirect:admin/create-film-page?success=film-created";
        } catch (NotUniqueValueException e) {
            return "redirect:admin/create-film-page?error=not-unique-film-value";
        }
    }

    private boolean isValidFilmDto(FilmDto filmDto) {
        return RegexUtil.matches("film.name.regex", filmDto.getName())
                && RegexUtil.matches("film.name.en.regex", filmDto.getNameEN())
                && RegexUtil.matches("film.genre.regex", filmDto.getGenre())
                && RegexUtil.matches("film.genre.en.regex", filmDto.getGenreEN())
                && RegexUtil.matches("film.director.regex", filmDto.getDirector())
                && RegexUtil.matches("film.director.en.regex", filmDto.getDirectorEN())
                && RegexUtil.matches("film.description.regex", filmDto.getDescription())
                && RegexUtil.matches("film.description.en.regex", filmDto.getDescriptionEN())
                && RegexUtil.matches("film.image.regex", filmDto.getImageLink())
                && RegexUtil.matches("film.image.en.regex", filmDto.getImageLinkEN())
                && filmDto.getRate() <= Constants.MAX_RATE
                && filmDto.getRate() >= Constants.MIN_RATE;
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
