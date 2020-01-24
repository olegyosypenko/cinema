package ua.training.model.service;

import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SeanceDao;
import ua.training.model.dao.TicketDao;
import ua.training.model.dao.Transaction;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dto.SeanceDto;
import ua.training.model.entity.Seance;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketService {
    private static final int NON_EXISTING_ID = -1;
    private Logger logger = Logger.getLogger(TicketService.class);

    public List<Ticket> getTicketsByUserId(int id) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            TicketDao ticketDao = daoFactory.createTicketDao();
            return ticketDao.getTicketsByUserId(id);
        }
    }

    public void buyTickets(List<Ticket> tickets) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        Transaction transaction = daoFactory.getTransaction();
        try {
            TicketDao ticketDao = daoFactory.createTicketDao();
            UserDao userDao = daoFactory.createUserDao();
            SeanceDao seanceDao = daoFactory.createSeanceDao();
            transaction.startTransaction();
            transaction.setSerializable();
            int userId = getUserId(tickets);
            int seanceId = getSeanceId(tickets);
            SeanceDto seanceDto = seanceDao.getSeanceDtoById(seanceId);
            User user = userDao.getUserById(userId);
            int fullPrice = seanceDto.getPrice() * tickets.size();
            int moneyAmount = user.getMoney();
            logger.debug("Amount of money: " + moneyAmount);
            logger.debug("Full price: " + fullPrice);
            if (moneyAmount < fullPrice) {
                transaction.rollback();
                throw new ServiceException("Not enough money");
            }
            List<Ticket> bookedTickets = ticketDao.getTicketsBySeanceId(seanceId);
            logger.debug("List of tickets by seances: " + bookedTickets);
            logger.debug("List of tickets that user wants to buy: " + tickets);
            if (checkIntersect(bookedTickets, tickets)) {
                transaction.rollback();
                throw new ServiceException("Ticket is taken");
            }
            ticketDao.createTickets(tickets);
            userDao.withdrawMoney(userId, fullPrice);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException("Could not complete transaction", e);
        } finally {
            transaction.close();
        }
    }

    private Integer getSeanceId(List<Ticket> tickets) {
        return tickets.stream().findAny().map(Ticket::getSeance).map(Seance::getId).orElse(NON_EXISTING_ID);
    }

    private Integer getUserId(List<Ticket> tickets) {
        return tickets.stream().findAny().map(Ticket::getUser).map(User::getId).orElse(NON_EXISTING_ID);
    }

    private boolean checkIntersect(List<Ticket> bookedTickets, List<Ticket> tickets) {
        Set<Ticket> bookedTicketsSet = new HashSet<>(bookedTickets);
        return tickets.stream().anyMatch(bookedTicketsSet::contains);
    }
}
