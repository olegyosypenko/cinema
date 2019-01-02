package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TicketDao;
import ua.training.model.entity.Ticket;

import java.util.List;

public class TicketService implements AutoCloseable {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    public List<Ticket> getTicketsBySeanceId(int id) {
        TicketDao ticketDao = daoFactory.createTicketDao();
        return ticketDao.getTicketsBySeanceId(id);
    }
    public List<Ticket> getTicketsByUserId(int id) {
        TicketDao ticketDao = daoFactory.createTicketDao();
        return ticketDao.getTicketsByUserId(id);
    }

    public void createTickets(List<Ticket> tickets) {
        TicketDao ticketDao = daoFactory.createTicketDao();
        ticketDao.createTickets(tickets);
    }
    public void close() {
        daoFactory.getTransaction().close();
    }
}
