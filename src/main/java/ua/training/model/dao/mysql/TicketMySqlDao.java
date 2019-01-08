package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.BundleHolder;
import ua.training.model.dao.TicketDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.entity.*;

import java.sql.*;
import java.util.*;

public class TicketMySqlDao implements TicketDao {
    private final Connection connection;
    private Logger logger = Logger.getLogger(TicketMySqlDao.class);

    TicketMySqlDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void deleteTicketsBySeanceId(int id) {
        String query = BundleHolder.getBundle().getString("delete.tickets.by.seance.id.query");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot delete tickets", e);
        }
    }
    @Override
    public List<Ticket> getTicketsBySeanceId(int id) {
        String query = BundleHolder.getBundle().getString("select.tickets.by.seance.id.query");
        ArrayList<Ticket> tickets = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                Seance seance = new Seance();
                User user = new User();
                seance.setId(id);
                seance.setPrice(resultSet.getInt(1));
                ticket.setRow(resultSet.getInt(2));
                ticket.setSeat(resultSet.getInt(3));
                user.setId(resultSet.getInt(4));
                ticket.setSeance(seance);
                ticket.setUser(user);
                tickets.add(ticket);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cannot execute query: " + query);
            throw new DaoException("Cannot execute query", e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByUserId(int userId) {
        List<Ticket> tickets = new ArrayList<>();
        String query = BundleHolder.getBundle().getString("select.tickets.by.user.id.query");
        Map<Integer, Seance> seances = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                if (seances.get(resultSet.getInt(1)) == null) {
                    Seance seance = new Seance();
                    Film film = new Film();
                    Hall hall = new Hall();
                    seance.setId(resultSet.getInt(1));
                    seance.setStartTime(resultSet.getTimestamp(2));
                    seance.setDuration(resultSet.getInt(3));
                    seance.setPrice(resultSet.getInt(4));
                    film.setName(resultSet.getString(5));
                    hall.setId(resultSet.getInt(6));
                    hall.setRows(resultSet.getInt(7));
                    hall.setRows(resultSet.getInt(8));
                    ticket.setSeance(seance);
                    seance.setFilm(film);
                    seances.put(seance.getId(), seance);
                }
                else {
                    ticket.setSeance(seances.get(resultSet.getInt(1)));
                }
                ticket.setRow(resultSet.getInt(9));
                ticket.setSeat(resultSet.getInt(10));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }
        return tickets;
    }

    @Override
    public void createTickets(List<Ticket> tickets) {
        String query = BundleHolder.getBundle().getString("insert.tickets.query");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (Ticket ticket : tickets) {
                statement.setInt(1, ticket.getUser().getId());
                statement.setInt(2, ticket.getSeance().getId());
                statement.setInt(3, ticket.getRow());
                statement.setInt(4, ticket.getSeat());
                statement.addBatch();
                statement.clearParameters();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("Cannot create tickets");
        }
    }
}
