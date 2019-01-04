package ua.training.model.service;

import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TicketDao;
import ua.training.model.dao.Transaction;
import ua.training.model.entity.Ticket;

import java.util.List;

public class TicketService implements AutoCloseable {
    private Logger logger = Logger.getLogger(TicketService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private Transaction transaction = daoFactory.getTransaction();
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
