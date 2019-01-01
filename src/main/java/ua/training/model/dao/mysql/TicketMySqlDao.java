package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.BundlePool;
import ua.training.model.dao.TicketDao;
import ua.training.model.entity.Film;
import ua.training.model.entity.Hall;
import ua.training.model.entity.Seance;
import ua.training.model.entity.Ticket;

import java.sql.*;
import java.util.*;

public class TicketMySqlDao implements TicketDao {
    private static final String GET_TICKETS_BY_SEANCE_ID = "SELECT tickets.row, tickets.seat FROM tickets WHERE seance_id = ?;";
    private static final String INSERT_TICKETS = "INSERT INTO tickets(user_id, seance_id, tickets.row, seat) VALUES (?, ?, ?, ?)";
    private final Connection connection;
    private Logger logger = Logger.getLogger(TicketMySqlDao.class);
    public TicketMySqlDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void createTicket(Ticket ticket) {

    }

    @Override
    public void deleteTicketsBySeanceId(int id) {

    }
    @Override
    public List<Ticket> getTicketsBySeanceId(int id) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try (PreparedStatement statement = connection.prepareStatement(GET_TICKETS_BY_SEANCE_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setRow(resultSet.getInt(1));
                ticket.setSeat(resultSet.getInt(2));
                tickets.add(ticket);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByUserId(int userId) {
        List<Ticket> tickets = new ArrayList<>();
        String query = BundlePool.getBundle().getString("select.tickets.by.id.query");
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
        try {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            String checkIfEnoughMoney = "SELECT * FROM users WHERE id = ? AND money >= (SELECT price FROM seances WHERE id = ?) * ?;"; //Todo put in bundle
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkIfEnoughMoney)) {
                preparedStatement.setInt(1, tickets.get(0).getUser().getId());
                preparedStatement.setInt(2, tickets.get(0).getSeance().getId());
                preparedStatement.setInt(3, tickets.size());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    throw new RuntimeException("Not enough money");
                }
            }

            String query = "SELECT * FROM tickets WHERE";
            for (int i = 0; i < tickets.size(); i++) {
                Ticket ticket = tickets.get(i);
                if (i == 0) {
                    query += " seance_id = " + ticket.getSeance().getId() + " AND seat = " + ticket.getSeat()
                            + " AND tickets.row = " + ticket.getRow();
                }
                else {
                    query += " OR seance_id = " + ticket.getSeance().getId() + " AND seat = " + ticket.getSeat()
                            + " AND tickets.row = " + ticket.getRow();
                }
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                throw new RuntimeException("The place is taken");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement statement = connection.prepareStatement(INSERT_TICKETS)) {
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
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement("UPDATE users SET money = money - ? WHERE id = ?")) {

            statement.setInt(1, tickets.size() * tickets.get(0).getSeance().getPrice());
            statement.setInt(2, tickets.get(0).getUser().getId());

            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
