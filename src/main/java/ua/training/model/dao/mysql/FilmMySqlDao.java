package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.BundleHolder;
import ua.training.model.dao.FilmDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.FilmDto;
import ua.training.model.entity.Film;
import ua.training.model.entity.Seance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FilmMySqlDao implements FilmDao {

    private final Connection connection;
    private final Logger logger = Logger.getLogger(FilmMySqlDao.class);

    public FilmMySqlDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Film getFilmById(int id) {
        String query = BundleHolder.getBundle().getString("select.film.by.id");
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
                film.setSeances(getSeancesFromResultSet(resultSet));
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Not unique value", e);
            throw new NotUniqueValueException(e);
        } catch (SQLException e) {
            logger.debug("Cannot execute query: " + query);
            throw new DaoException("Cannot execute query", e);
        }
        return film;
    }

    private List<Seance> getSeancesFromResultSet(ResultSet resultSet) throws SQLException {
        List<Seance> seances = new ArrayList<>();
        if (resultSet.getInt(8) != 0) {
            Seance seance = new Seance();
            seance.setId(resultSet.getInt(8));
            seance.setStartTime(resultSet.getTimestamp(9));
            seances.add(seance);
        }
        while (resultSet.next()) {
            Seance seance = new Seance();
            seance.setId(resultSet.getInt(8));
            seance.setStartTime(resultSet.getTimestamp(9));
            seances.add(seance);
        }
        return seances;
    }

    @Override
    public List<Film> getAllFilms() {
        String query = BundleHolder.getBundle().getString("select.all-users.query");
        List<Film> films = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
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
            logger.error("Cannot execute query: " + query, e);
            throw new DaoException("Cannot execute query", e);
        }
        return films;
    }

    @Override
    public void createFilm(FilmDto film) {
        String query = BundleHolder.getBundle().getString("create.film.query");
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
        }  catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Not unique value", e);
            throw new NotUniqueValueException(e);
        }
        catch (SQLException e) {
            logger.error("Cannot execute query: " + query, e);
            throw new DaoException("Cannot execute query", e);
        }
    }

    @Override
    public void deleteFilmById(int id) {
        String query = BundleHolder.getBundle().getString("delete.film.query");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Cannot execute query: " + query, e);
            throw new DaoException("Cannot execute query", e);
        }
    }

    @Override
    public List<Film> getMostPopularFilms() {
        logger.trace("getMostPopularFilms start");
        String query = BundleHolder.getBundle().getString("select.most.popular.films.query");
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        int topFilmsNumber = Integer.parseInt(bundle.getString("top.films.number"));
        List<Film> films = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, topFilmsNumber);
            ResultSet resultSet = statement.executeQuery();
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
            logger.debug("Cannot execute query: " + query);
            throw new DaoException("Cannot execute query", e);
        }
        logger.trace("getMostPopularFilms end");
        return films;
    }
}
