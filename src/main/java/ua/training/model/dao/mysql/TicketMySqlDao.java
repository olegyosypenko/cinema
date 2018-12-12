package ua.training.model.dao.mysql;

import ua.training.model.dao.TicketDao;
import ua.training.model.entity.Ticket;

import java.util.List;

public class TicketMySqlDao implements TicketDao {
    @Override
    public List<Ticket> getTicketsByUserId(int id) {
        return null;
    }

    @Override
    public void createTicket(Ticket ticket) {

    }

    @Override
    public void deleteTicketsBySeanceId(int id) {

    }
}
