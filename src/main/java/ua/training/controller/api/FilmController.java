package ua.training.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.training.model.entity.Film;
import ua.training.model.service.FilmService;

import java.util.List;

@RestController
public class FilmController {

    private FilmService filmService = new FilmService();

    @GetMapping(value = "films")
    public List<Film> getFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping(value = "films/{id}")
    public Film getFilm(@PathVariable("id") int id) {
        return filmService.getFilmById(id);
    }

    @GetMapping(value = "films/most-popular", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> getMostPopularFilms() {
        return filmService.getMostPopularFilms();
    }
}
