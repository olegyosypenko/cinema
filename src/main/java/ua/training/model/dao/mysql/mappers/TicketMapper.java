package ua.training.model.dao.mysql.mappers;

import ua.training.model.entity.Seance;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper {
    public static Ticket mapToTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setRow(resultSet.getInt(8));
        ticket.setSeat(resultSet.getInt(9));
        User user = new User.Builder().setId(resultSet.getInt(10)).build();
        Seance seance = new Seance();
        seance.setId(resultSet.getInt(11));
        ticket.setUser(user);
        ticket.setSeance(seance);
        return ticket;
    }
}
