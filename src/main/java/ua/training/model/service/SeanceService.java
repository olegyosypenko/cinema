package ua.training.model.service;

import ua.training.model.dao.*;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dto.SeanceDto;
import ua.training.model.entity.Seance;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;

import java.sql.Date;
import java.util.ArrayList;
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
        try (Transaction transaction = daoFactory.getTransaction()) {
            TicketDao ticketDao = daoFactory.createTicketDao();
            SeanceDao seanceDao = daoFactory.createSeanceDao();
            UserDao userDao = daoFactory.createUserDao();
            transaction.startTransaction();
            transaction.setSerializable();

            List<Ticket> tickets = ticketDao.getTicketsBySeanceId(seanceId);
            if (tickets.isEmpty()) {
                seanceDao.deleteSeanceById(seanceId);
            } else {
                int price = tickets.get(0).getSeance().getPrice();
                Map<User, Integer> refundForEachUser = calculateRefundForEachUser(tickets, price);
                try {
                    userDao.addMoneyToUsers(refundForEachUser);
                    ticketDao.deleteTicketsBySeanceId(seanceId);
                    seanceDao.deleteSeanceById(seanceId);
                } catch (DaoException e) {
                    transaction.rollback();
                    throw new ServiceException("Cannot delete seance", e);
                }
            }
            transaction.commit();
        }
    }

    private Map<User, Integer> calculateRefundForEachUser(List<Ticket> tickets, int price) {
        Map<User, List<Ticket>> userTickets = tickets.stream().collect(Collectors.groupingBy(Ticket::getUser));
        return userTickets.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (entry.getValue().size() + 1) * price));
    }

    public SeanceDto getSeanceDtoById(int seanceId) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            SeanceDao seanceDao = daoFactory.createSeanceDao();
            return seanceDao.getSeanceDtoById(seanceId);
        }
    }
}