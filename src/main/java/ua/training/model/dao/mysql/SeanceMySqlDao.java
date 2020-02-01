package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.BundleHolder;
import ua.training.model.dao.SeanceDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dao.mysql.mappers.SeanceMapper;
import ua.training.model.dao.mysql.mappers.TicketMapper;
import ua.training.model.dto.SeanceDto;
import ua.training.model.entity.Seance;
import ua.training.model.entity.Ticket;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SeanceMySqlDao implements SeanceDao {
    private final Connection connection;
    private Logger logger = Logger.getLogger(SeanceMySqlDao.class);

    SeanceMySqlDao(Connection connection) {
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
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
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
                SeanceDto seanceDto = SeanceMapper.mapToSeanceDto(resultSet);
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
        SeanceDto seanceDto;
        try (PreparedStatement statement = connection.prepareStatement(getSeancesByDate)) {
            statement.setInt(1, seanceId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                seanceDto = SeanceMapper.mapToSeanceDto(resultSet);
                seanceDto.setTickets(getTickets(resultSet));
            } else {
                logger.debug("Seance id: " + seanceId);
                throw new DaoException("There is no seance with this id");
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
        return seanceDto;
    }

    private List<Ticket> getTickets(ResultSet resultSet) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        if (resultSet.getInt(8) != 0) {
            do {
                tickets.add(TicketMapper.mapToTicket(resultSet));
            } while (resultSet.next());
        }
        return tickets;
    }

    @Override
    public void deleteSeanceById(int seanceId) {

        String query = BundleHolder.getBundle().getString("delete.seance.by.id.query");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, seanceId);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot delete seance", e);
        }
    }
}
