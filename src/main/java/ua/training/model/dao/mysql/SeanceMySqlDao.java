package ua.training.model.dao.mysql;

import ua.training.model.dao.SeanceDao;
import ua.training.model.entity.Role;
import ua.training.model.entity.Seance;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.List;

public class SeanceMySqlDao implements SeanceDao {
    private static final String CREATE_SEANCE_QUERY = "INSERT INTO seances(start_time, end_time, price, money_collected, " +
            "film_id, hall_id) VALUES (?, ?, ?, ?, ?, ?);";
    private Connection connection = ConnectionPool.getConnection();
    @Override
    public void createSeance(Seance seance) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_SEANCE_QUERY)) {
            statement.setDate(1, seance.getStartTime());
            statement.setDate(2, seance.getEndTime());
            statement.setInt(3, seance.getPrice());
            statement.setInt(4, 0);
            statement.setInt(5, seance.getFilm().getId());
            statement.setInt(6, 1);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Seance> getSeancesByFilmId(int id) {
        return null;
    }

    @Override
    public List<Seance> getSeancesByDate(Date date) {
        return null;
    }

    @Override
    public void deleteSeanceById(int id) {

    }
}
