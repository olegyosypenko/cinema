package ua.training.model.dao.mysql.mappers;

import ua.training.model.entity.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmMapper {

    /**
     * Creates film from result set
     *
     * @param resultSet resultSet from DB
     * @return User
     */
    public static Film mapFilm(ResultSet resultSet) throws SQLException {
        return new Film.Builder()
                .setId(resultSet.getInt(1))
                .setName(resultSet.getString(2))
                .setGenre(resultSet.getString(3))
                .setDirector(resultSet.getString(4))
                .setRate(resultSet.getFloat(5))
                .setDescription(resultSet.getString(6))
                .setImageLink(resultSet.getString(7))
                .buildFilm();
    }
}
