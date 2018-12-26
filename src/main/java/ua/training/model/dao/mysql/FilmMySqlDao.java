package ua.training.model.dao.mysql;

import ua.training.model.BundlePool;
import ua.training.model.dao.FilmDao;
import ua.training.model.dto.FilmDto;
import ua.training.model.entity.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmMySqlDao implements FilmDao {
    private Connection connection = ConnectionPool.getConnection();
    public static final String CREATE_FILM = "INSERT INTO films(name, name_en, genre, genre_en, director, " +
            "director_en, rate, description, description_en, image_link, image_link_en) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String GET_LAST_ID = "SELECT id FROM films WHERE name=?;";
    public static final String GET_ALL_FILMS = "SELECT * FROM films;";

    @Override
    public Film getFilmById(int id) {
        return null;
    }

    @Override
    public List<Film> getAllFilms() {
        String getAllFilms = BundlePool.instance.getBundleByThreadName(Thread.currentThread().getName())
                .getString("select.all-users.query");
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
            throw new RuntimeException(e);
        }
        return films;
    }

    @Override
    public void createFilm(FilmDto film) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_FILM)) {
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
        }
    }
}
