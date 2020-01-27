package ua.training.model.service;

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

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeanceService {

    public void createSeance(Seance seance) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            SeanceDao seanceDao = daoFactory.createSeanceDao();
            seanceDao.createSeance(seance);
        }
    }

    public List<SeanceDto> getSeancesByDate(Date date) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            SeanceDao seanceDao = daoFactory.createSeanceDao();
            return seanceDao.getSeancesByDate(date);
        }
    }

    public void deleteSeanceById(int seanceId) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        Transaction transaction = daoFactory.getTransaction();
        try {
            TicketDao ticketDao = daoFactory.createTicketDao();
            SeanceDao seanceDao = daoFactory.createSeanceDao();
            UserDao userDao = daoFactory.createUserDao();
            transaction.startTransaction();
            transaction.setSerializable();
            List<Ticket> tickets = ticketDao.getTicketsBySeanceId(seanceId);
            if (!tickets.isEmpty()) {
                refundMoney(userDao, tickets);
                ticketDao.deleteTicketsBySeanceId(seanceId);
            }
            seanceDao.deleteSeanceById(seanceId);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException("Cannot delete seance", e);
        } finally {
            transaction.close();
        }
    }

    private void refundMoney(UserDao userDao, List<Ticket> tickets) {
        Map<User, Integer> refundForEachUser = calculateRefundForEachUser(tickets);
        userDao.addMoneyToUsers(refundForEachUser);
    }

    private Map<User, Integer> calculateRefundForEachUser(List<Ticket> tickets) {
        int price = getPrice(tickets);
        Map<User, List<Ticket>> userTickets = tickets.stream().collect(Collectors.groupingBy(Ticket::getUser));
        return userTickets.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (entry.getValue().size() + 1) * price));
    }

    private int getPrice(List<Ticket> tickets) {
        return tickets.get(0).getSeance().getPrice();
    }

    public SeanceDto getSeanceDtoById(int seanceId) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            SeanceDao seanceDao = daoFactory.createSeanceDao();
            return seanceDao.getSeanceDtoById(seanceId);
        }
    }
}