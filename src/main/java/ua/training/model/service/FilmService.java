package ua.training.model.service;

import ua.training.model.dao.FilmDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.mysql.FilmMySqlDao;
import ua.training.model.dao.mysql.UserMySqlDao;
import ua.training.model.entity.Film;
import ua.training.model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Filter;

public class FilmService {

    FilmDao filmDao = new FilmMySqlDao();
    public List<Film> getAllFilms() throws SQLException {
        return filmDao.getAllFilms();
    }
    public void createFilm(Film film) throws SQLException {
        filmDao.createFilm(film);
    }
}
