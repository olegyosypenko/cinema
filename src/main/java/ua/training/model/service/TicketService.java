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
        try (Transaction transaction = daoFactory.getTransaction()) {
            TicketDao ticketDao = daoFactory.createTicketDao();
            UserDao userDao = daoFactory.createUserDao();
            SeanceDao seanceDao = daoFactory.createSeanceDao();
            transaction.startTransaction();
            transaction.setSerializable();
            try {
                int id = tickets.stream().findAny().map(Ticket::getUser).map(User::getId).orElse(NON_EXISTING_ID);
                int seanceId = tickets.stream().findAny().map(Ticket::getSeance).map(Seance::getId).orElse(NON_EXISTING_ID);
                SeanceDto seanceDto = seanceDao.getSeanceDtoById(id);
                int priceOfTicket = seanceDto.getPrice();
                int fullPrice = priceOfTicket * tickets.size();
                User user = userDao.getUserById(id);
                int moneyAmount = user.getMoney();
                logger.debug("Amount of money: " + moneyAmount);
                logger.debug("Full price: " + fullPrice);
                if (moneyAmount < fullPrice) {
                    transaction.rollback();
                    throw new ServiceException("Not enough money");
                }
                List<Ticket> list = ticketDao.getTicketsBySeanceId(seanceId);
                logger.debug("List of tickets by seances: " + list);
                logger.debug("List of tickets that user wants to buy: " + ticketDao);
                if (checkIntersect(list, tickets)) {
                    transaction.rollback();
                    throw new ServiceException("Ticket is taken");
                }
                ticketDao.createTickets(tickets);
                userDao.withdrawMoney(id, fullPrice);
            } catch (DaoException e) {
                transaction.rollback();
                throw new DaoException("Could not complete transaction", e);
            }
            transaction.commit();
        }
    }

    private boolean checkIntersect(List<Ticket> list, List<Ticket> tickets) {
        Set<Ticket> ticketSet = new HashSet<>(list);
        for (Ticket ticket : tickets) {
            if (ticketSet.contains(ticket)) {
                return true;
            }
        }
        return false;
    }
}
