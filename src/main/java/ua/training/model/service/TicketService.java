package ua.training.model.service;

import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TicketDao;
import ua.training.model.dao.Transaction;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.entity.Ticket;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketService {
    private Logger logger = Logger.getLogger(TicketService.class);
    public List<Ticket> getTicketsBySeanceId(int id) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            TicketDao ticketDao = daoFactory.createTicketDao();
            return ticketDao.getTicketsBySeanceId(id);
        }
    }
    public List<Ticket> getTicketsByUserId(int id) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            TicketDao ticketDao = daoFactory.createTicketDao();
            return ticketDao.getTicketsByUserId(id);
        }
    }

    public void createTickets(List<Ticket> tickets) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction transaction = daoFactory.getTransaction()) {
            TicketDao ticketDao = daoFactory.createTicketDao();
            UserDao userDao = daoFactory.createUserDao();
            transaction.startTransaction();
            transaction.setSerializable();
            try {
                int id = -1;
                int seanceId = -1;
                int priceOfTicket = 0;
                if (tickets.size() > 0) {
                    id = tickets.get(0).getUser().getId();
                    seanceId = tickets.get(0).getSeance().getId();
                    priceOfTicket = tickets.get(0).getSeance().getPrice();
                }
                int fullPrice = priceOfTicket * tickets.size();
                int moneyAmount = userDao.getMoneyAmountById(id);
                if (moneyAmount < fullPrice) {
                    logger.debug("Amount of money: " + moneyAmount);
                    logger.debug("Full price: " + fullPrice);
                    transaction.rollback();
                    throw new ServiceException("Not enough money");
                }
                List<Ticket> list = ticketDao.getTicketsBySeanceId(seanceId);
                if (checkIntersect(list, tickets)) {
                    logger.debug("List of tickets by seances: " + list);
                    logger.debug("List of tickets that user wants to buy: " + ticketDao);
                    transaction.rollback();
                    throw new ServiceException("Ticket is taken");
                }
                ticketDao.createTickets(tickets);
                userDao.withdrawMoney(id, fullPrice);
            } catch (DaoException e) {
                transaction.rollback();
                throw new DaoException();
            }
            transaction.commit();
        }
    }

    private boolean checkIntersect(List<Ticket> list, List<Ticket> tickets) {
        Set<Ticket> ticketSet = new HashSet<>(list);
        System.out.println(list);
        System.out.println(tickets);
        for (Ticket ticket : tickets) {
            if (ticketSet.contains(ticket)) {
                return true;
            }
        }
        return false;
    }
}
