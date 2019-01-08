package ua.training.model.dao.mysql;

import org.junit.Test;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.FilmDto;
import ua.training.model.entity.Film;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilmMySqlDaoTest {

    @Test
    public void getFilmById() throws SQLException {
        FilmMySqlDao filmMySqlDao = new FilmMySqlDao(ConnectionPool.getDataSource().getConnection());
        Film film = filmMySqlDao.getFilmById(30);
        assertEquals(6.4, film.getRate(), 0.01);
    }

    @Test
    public void getAllFilms() throws SQLException {
        FilmMySqlDao filmMySqlDao = new FilmMySqlDao(ConnectionPool.getDataSource().getConnection());
        assertTrue(filmMySqlDao.getAllFilms().size() > 10);
    }

    @Test
    public void getMostPopularFilms() throws SQLException {
        FilmMySqlDao filmMySqlDao = new FilmMySqlDao(ConnectionPool.getDataSource().getConnection());
        assertTrue(filmMySqlDao.getAllFilms().size() > 1);
    }

    @Test(expected = NotUniqueValueException.class)
    public void createFilm() throws SQLException {
        FilmMySqlDao filmMySqlDao = new FilmMySqlDao(ConnectionPool.getDataSource().getConnection());
        FilmDto filmDto = new FilmDto();
        filmDto.setName("Бамблбі");
        filmMySqlDao.createFilm(filmDto);
    }
}