package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.BundleHolder;
import ua.training.model.dao.SeanceDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dto.SeanceDto;
import ua.training.model.entity.Seance;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceMySqlDao implements SeanceDao {
    private final Connection connection;
    private Logger logger = Logger.getLogger(SeanceMySqlDao.class);

    public SeanceMySqlDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createSeance(Seance seance) {
        String query = BundleHolder.getBundle().getString("create.seance.query");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, seance.getStartTime());
            statement.setInt(2, 120);
            statement.setInt(3, seance.getPrice());
            statement.setInt(4, seance.getFilm().getId());
            statement.setInt(5, 1);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot create seance", e);
        }
    }

    @Override
    public List<Seance> getSeancesByFilmId(int id) {
        String query = BundleHolder.getBundle().getString("select.seances.by.film.id.query");
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
            throw new DaoException("Cannot execute query", e);
        }
        return list;
    }

    @Override
    public List<SeanceDto> getSeancesByDate(Date date) {
        String getSeancesByDate = BundleHolder.getBundle().getString("select.seances.by.date.query");
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
            throw new DaoException("Cannot find seances by date", e);
        }
        return seances;
    }

    @Override
    public SeanceDto getSeanceDtoById(int seanceId) {
        String getSeancesByDate = BundleHolder.getBundle().getString("select.seance.by.id.query");
        SeanceDto seanceDto = new SeanceDto();
        try (PreparedStatement statement = connection.prepareStatement(getSeancesByDate)) {
            statement.setInt(1, seanceId);
            ResultSet resultSet = statement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            seanceDto.setTickets(tickets);
            if (resultSet.next()) {
                seanceDto.setId(resultSet.getInt(1));
                seanceDto.setStartTime(resultSet.getTimestamp(2));
                seanceDto.setDuration(resultSet.getInt(3));
                seanceDto.setPrice(resultSet.getInt(4));
                seanceDto.setName(resultSet.getString(5));
                seanceDto.setColumns(resultSet.getInt(6));
                seanceDto.setRows(resultSet.getInt(7));
                Ticket ticket = new Ticket();
                if (resultSet.getInt(8) != 0) {
                    tickets.add(ticket);
                    ticket.setRow(resultSet.getInt(8));
                    ticket.setSeat(resultSet.getInt(9));
                    User user = new User();
                    Seance seance = new Seance();
                    user.setId(resultSet.getInt(10));
                    seance.setId(resultSet.getInt(11));
                    ticket.setUser(user);
                    ticket.setSeance(seance);
                }
            } else {
                logger.debug("Seance id: " + seanceId);
                throw new DaoException("There is no seance with this id");
            }
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                tickets.add(ticket);
                ticket.setRow(resultSet.getInt(8));
                ticket.setSeat(resultSet.getInt(9));
                User user = new User();
                Seance seance = new Seance();
                user.setId(resultSet.getInt(10));
                seance.setId(resultSet.getInt(11));
                ticket.setUser(user);
                ticket.setSeance(seance);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
        return seanceDto;
    }
    @Override
    public void getSeanceParametersById(int seanceId) {

    }
    @Override
    public void deleteSeanceById(int seanceId) {

        String query = BundleHolder.getBundle().getString("delete.seance.by.id.query");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, seanceId);
            statement.execute();
        } catch (SQLException e) {
            logger.error("Cannot delete seance by id: " + seanceId, e);
            throw new DaoException("Cannot delete seance", e);
        }
    }
}
