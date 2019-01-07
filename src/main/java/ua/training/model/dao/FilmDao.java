package ua.training.model.dao;

import ua.training.model.dto.FilmDto;
import ua.training.model.entity.Film;

import java.util.List;

public interface FilmDao {
    Film getFilmById(int id);
    List<Film> getAllFilms();
    void createFilm(FilmDto film);
    void deleteFilmById(int id);
    List<Film> getMostPopularFilms();
}
