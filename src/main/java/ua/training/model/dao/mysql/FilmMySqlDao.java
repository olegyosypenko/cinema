package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.BundlePool;
import ua.training.model.dao.FilmDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.FilmDto;
import ua.training.model.entity.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmMySqlDao implements FilmDao {

    private final Connection connection;
    private final Logger logger = Logger.getLogger(FilmMySqlDao.class);

    public FilmMySqlDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Film getFilmById(int id) {
        String query = BundlePool.getBundle().getString("select.film.by.id");
        Film film = new Film();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                film.setId(resultSet.getInt(1));
                film.setName(resultSet.getString(2));
                film.setGenre(resultSet.getString(3));
                film.setDirector(resultSet.getString(4));
                film.setRate(resultSet.getFloat(5));
                film.setDescription(resultSet.getString(6));
                film.setImageLink(resultSet.getString(7));
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Not unique value", e);
            throw new NotUniqueValueException(e);
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
        return film;
    }

    @Override
    public List<Film> getAllFilms() {
        String getAllFilms = BundlePool.getBundle().getString("select.all-users.query");
        List<Film> films = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllFilms);
            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt(1));
                film.setName(resultSet.getString(2));
                film.setGenre(resultSet.getString(3));
                film.setDirector(resultSet.getString(4));
                film.setRate(resultSet.getFloat(5));
                film.setDescription(resultSet.getString(6));
                film.setImageLink(resultSet.getString(7));
                films.add(film);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
        return films;
    }

    @Override
    public void createFilm(FilmDto film) {
        String query = BundlePool.getBundle().getString("create.film.query");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, film.getName());
            statement.setString(2, film.getNameEN());
            statement.setString(3, film.getGenre());
            statement.setString(4, film.getGenreEN());
            statement.setString(5, film.getDirector());
            statement.setString(6, film.getDirectorEN());
            statement.setFloat(7, film.getRate());
            statement.setString(8, film.getDescription());
            statement.setString(9, film.getDescriptionEN());
            statement.setString(10, film.getImageLink());
            statement.setString(11, film.getImageLinkEN());
            statement.execute();
        } catch (SQLException e) {

        }
    }

    @Override
    public void deleteFilmById(int id) {
        String query = BundlePool.getBundle().getString("delete.film.query");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
    }
}
