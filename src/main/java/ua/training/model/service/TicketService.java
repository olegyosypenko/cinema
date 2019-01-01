package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TicketDao;
import ua.training.model.entity.Ticket;

import java.util.List;

public class TicketService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    public List<Ticket> getTicketsBySeanceId(int id) {
        try (TicketDao ticketDao = daoFactory.createTicketDao()) {
            return ticketDao.getTicketsBySeanceId(id);
        }
    }
    public List<Ticket> getTicketsByUserId(int id) {
        try (TicketDao ticketDao = daoFactory.createTicketDao()) {
            return ticketDao.getTicketsByUserId(id);
        }
    }

    public void createTickets(List<Ticket> tickets) {
        try (TicketDao ticketDao = daoFactory.createTicketDao()) {
            ticketDao.createTickets(tickets);
        }
    }
}
