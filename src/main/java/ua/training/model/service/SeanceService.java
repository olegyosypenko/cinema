package ua.training.model.service;

import ua.training.model.dao.*;
import ua.training.model.dto.SeanceDto;
import ua.training.model.entity.Hall;
import ua.training.model.entity.Seance;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeanceService implements AutoCloseable {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void createSeance(Seance seance) {
        SeanceDao seanceDao = daoFactory.createSeanceDao();
        seanceDao.createSeance(seance);
    }
    public List<ua.training.model.dto.SeanceDto> getSeancesByDate(Date date) {
        SeanceDao seanceDao = daoFactory.createSeanceDao();
            return seanceDao.getSeancesByDate(date);
    }
    public List<Seance> getSeancesByFilmId(int id) {
        SeanceDao seanceDao = daoFactory.createSeanceDao();
        return seanceDao.getSeancesByFilmId(id);
    }

    public void deleteSeanceById(int seanceId) {
        TicketDao ticketDao = daoFactory.createTicketDao();
        SeanceDao seanceDao = daoFactory.createSeanceDao();
        UserDao userDao = daoFactory.createUserDao();
        Transaction transaction = daoFactory.getTransaction();
        transaction.startTransaction();
        transaction.setSerializable();

        List<Ticket> tickets = ticketDao.getTicketsBySeanceId(seanceId);
        if (tickets.isEmpty()) {
            seanceDao.deleteSeanceById(seanceId);
        } else {
            int price = tickets.get(0).getSeance().getPrice();
            Map<User, Integer> numberOfTicketsForEachUser = getNumberOfTicketsForEachUser(tickets);
            List<User> users = new ArrayList<>();
            List<Integer> money = new ArrayList<>();
            numberOfTicketsForEachUser.entrySet().forEach((entry) -> {
                users.add(entry.getKey());
                money.add(price * entry.getValue());
            });
            userDao.addMoneyToUsers(users, money);
            ticketDao.deleteTicketsBySeanceId(seanceId);
            seanceDao.deleteSeanceById(seanceId);
        }
        transaction.commit();
        transaction.close();
    }

    private Map<User, Integer> getNumberOfTicketsForEachUser(List<Ticket> tickets) {
        Map<User, Integer> uniqueUsers = new HashMap<>();
        tickets.forEach((ticket) -> {
            if (uniqueUsers.containsKey(ticket.getUser())) {
                uniqueUsers.put(ticket.getUser(), uniqueUsers.get(ticket.getUser()) + 1);
            } else {
                uniqueUsers.put(ticket.getUser(), 1);
            }
        });
        return uniqueUsers;
    }

    public SeanceDto getSeanceDtoById(int seanceId) {
        SeanceDao seanceDao = daoFactory.createSeanceDao();
        return seanceDao.getSeanceById(seanceId);
    }

    public Seance getSeanceById(int seanceId) {
        Seance seance = new Seance();
        SeanceDto seanceDto = getSeanceDtoById(seanceId);
        Hall hall = new Hall();
        hall.setColumns(seanceDto.getColumns());
        hall.setRows(seanceDto.getRows());
        seance.setId(seanceId);
        seance.setPrice(seanceDto.getPrice());
        seance.setDuration(seanceDto.getDuration());
        seance.setStartTime(seanceDto.getStartTime());
        seance.setHall(hall);
        return seance;
    }

    public void close() {
        daoFactory.getTransaction().close();
    }

}