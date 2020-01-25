package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.BundleHolder;
import ua.training.model.dao.FilmDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dao.mysql.mappers.FilmMapper;
import ua.training.model.dto.FilmDto;
import ua.training.model.entity.Film;
import ua.training.model.entity.Seance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FilmMySqlDao implements FilmDao {

    private static final Logger logger = Logger.getLogger(FilmMySqlDao.class);
    private static final int SEANCE_ID_INDEX = 8;
    private final Connection connection;

    public FilmMySqlDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Film getFilmById(int id) {
        String query = BundleHolder.getBundle().getString("select.film.by.id");
        Film film = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                film = new Film.Builder()
                        .setId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setGenre(resultSet.getString(3))
                        .setDirector(resultSet.getString(4))
                        .setRate(resultSet.getFloat(5))
                        .setDescription(resultSet.getString(6))
                        .setImageLink(resultSet.getString(7))
                        .setSeances(getSeancesFromResultSet(resultSet))
                        .buildFilm();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new NotUniqueValueException(e);
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
        return film;
    }

    private List<Seance> getSeancesFromResultSet(ResultSet resultSet) throws SQLException {
        List<Seance> seances = new ArrayList<>();
        if (isSeanceFound(resultSet)) {
            do {
                seances.add(createSeanceFromResultSet(resultSet));
            } while (resultSet.next());
        }
        return seances;
    }

    private boolean isSeanceFound(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(SEANCE_ID_INDEX) != 0;
    }

    private Seance createSeanceFromResultSet(ResultSet resultSet) throws SQLException {
        Seance seance = new Seance();
        seance.setId(resultSet.getInt(SEANCE_ID_INDEX));
        seance.setStartTime(resultSet.getTimestamp(9));
        return seance;
    }

    @Override
    public List<Film> getAllFilms() {
        String query = BundleHolder.getBundle().getString("select.all-users.query");
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            return extractFilmsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
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
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new NotUniqueValueException(e);
        } catch (SQLException e) {
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
            throw new DaoException("Cannot execute query", e);
        }
    }

    @Override
    public List<Film> getMostPopularFilms() {
        logger.trace("getMostPopularFilms start");
        String query = BundleHolder.getBundle().getString("select.most.popular.films.query");
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        int topFilmsNumber = Integer.parseInt(bundle.getString("top.films.number"));
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, topFilmsNumber);
            ResultSet resultSet = statement.executeQuery();
            return extractFilmsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
    }

    private List<Film> extractFilmsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Film> films = new ArrayList<>();
        while (resultSet.next()) {
            Film film = FilmMapper.mapFilm(resultSet);
            films.add(film);
        }
        return films;
    }
}
