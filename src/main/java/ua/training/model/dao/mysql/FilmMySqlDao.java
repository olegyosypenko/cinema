package ua.training.model.dao.mysql;

import ua.training.model.dao.FilmDao;
import ua.training.model.entity.Film;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmMySqlDao implements FilmDao {
    private Connection connection = ConnectionPool.getConnection();
    public static final String CREATE_FILM = "INSERT INTO films(name, name_en, genre, genre_en, director, " +
            "director_en, rate, description, description_en) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String GET_LAST_ID = "SELECT id FROM films WHERE name=?;";
    public static final String GET_ALL_FILMS = "SELECT * FROM films;";

    @Override
    public Film getFilmById(int id) {
        return null;
    }

    @Override
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<Film>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_FILMS);
            while (resultSet.next()) {
                Film film = new Film();
                film.setName(resultSet.getString(2));
                film.setNameEN(resultSet.getString(3));
                film.setGenre(resultSet.getString(4));
                film.setGenreEN(resultSet.getString(5));
                film.setDirector(resultSet.getString(6));
                film.setDirectorEN(resultSet.getString(7));
                film.setRate(resultSet.getFloat(8));
                film.setDescription(resultSet.getString(9));
                film.setDescriptionEN(resultSet.getString(10));
                films.add(film);
            }
        } catch (SQLException e) {
            System.out.println("OOpsie");
        }
        return films;
    }

        @Override
    public void createFilm(Film film) throws SQLException {
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
            statement.execute();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_LAST_ID)) {
            statement.setString(1, film.getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                film.setId(rs.getInt(1));
            }
        }
        connection.close();
    }
}
