package ua.training.model.service;

import ua.training.model.dao.FilmDao;
import ua.training.model.dao.mysql.FilmMySqlDao;
import ua.training.model.dto.FilmDto;
import ua.training.model.entity.Film;

import java.sql.SQLException;
import java.util.List;

public class FilmService {

    FilmDao filmDao = new FilmMySqlDao();
    public List<Film> getAllFilms() throws SQLException {
        return filmDao.getAllFilms();
    }
    public void createFilm(FilmDto film) throws SQLException {
        filmDao.createFilm(film);
    }
}
