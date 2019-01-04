package ua.training.model.dao.mysql;

import ua.training.model.BundlePool;
import ua.training.model.dao.SeanceDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dto.SeanceDto;
import ua.training.model.entity.Seance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceMySqlDao implements SeanceDao {
    private static final String CREATE_SEANCE_QUERY = "INSERT INTO seances(start_time, duration, price, money_collected, " +
            "film_id, hall_id) VALUES (?, ?, ?, ?, ?, ?);";
    private final Connection connection;

    public SeanceMySqlDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createSeance(Seance seance) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_SEANCE_QUERY)) {
            statement.setTimestamp(1, seance.getStartTime());
            statement.setInt(2, 120);
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
        String query = BundlePool.getBundle().getString("select.seances.by.film.id.query");
        List<Seance> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Seance seance = new Seance();
                seance.setId(resultSet.getInt(1));
                seance.setStartTime(resultSet.getTimestamp(2));
                seance.setDuration(resultSet.getInt(3));
                seance.setPrice(resultSet.getInt(4));
                list.add(seance);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find seances");
        }
        return list;
    }

    @Override
    public List<SeanceDto> getSeancesByDate(Date date) {
        String getSeancesByDate = BundlePool.getBundle().getString("select.seances.by.date.query");
        System.out.println(getSeancesByDate);
        List<SeanceDto> seances = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(getSeancesByDate)) {
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SeanceDto seanceDto = new SeanceDto();
                seanceDto.setId(resultSet.getInt(1));
                seanceDto.setStartTime(resultSet.getTimestamp(2));
                seanceDto.setDuration(resultSet.getInt(3));
                seanceDto.setPrice(resultSet.getInt(4));
                seanceDto.setName(resultSet.getString(5));
                seanceDto.setColumns(resultSet.getInt(6));
                seanceDto.setRows(resultSet.getInt(7));

                seances.add(seanceDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seances;
    }


    @Override
    public SeanceDto getSeanceById(int seanceId) {
        String getSeancesByDate = BundlePool.getBundle().getString("select.seance.by.id.query");
        SeanceDto seanceDto = new SeanceDto();
        try (PreparedStatement statement = connection.prepareStatement(getSeancesByDate)) {
            statement.setInt(1, seanceId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                seanceDto.setId(resultSet.getInt(1));
                seanceDto.setStartTime(resultSet.getTimestamp(2));
                seanceDto.setDuration(resultSet.getInt(3));
                seanceDto.setPrice(resultSet.getInt(4));
                seanceDto.setName(resultSet.getString(5));
                seanceDto.setColumns(resultSet.getInt(6));
                seanceDto.setRows(resultSet.getInt(7));
            } else {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seanceDto;
    }
    @Override
    public void getSeanceParametersById(int seanceId) {

    }
    @Override
    public void deleteSeanceById(int seanceId) {

        String query = BundlePool.getBundle().getString("delete.seance.by.id.query");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, seanceId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
