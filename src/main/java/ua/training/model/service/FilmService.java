package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.FilmDao;
import ua.training.model.dao.mysql.FilmMySqlDao;
import ua.training.model.dto.FilmDto;
import ua.training.model.entity.Film;

import java.sql.SQLException;
import java.util.List;

public class FilmService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Film> getAllFilms() {
        try (FilmDao filmDao = daoFactory.createFilmDao()) {
            return filmDao.getAllFilms();
        }

    }
    public void createFilm(FilmDto film) {
        try (FilmDao filmDao = daoFactory.createFilmDao()) {
            filmDao.createFilm(film);
        }
    }

    public Film getFilmById(int filmId) {
        try (FilmDao filmDao = daoFactory.createFilmDao()) {
            return filmDao.getFilmById(filmId);
        }
    }
}
