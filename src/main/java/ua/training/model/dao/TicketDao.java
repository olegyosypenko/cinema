package ua.training.model.dao;

import ua.training.model.entity.Ticket;

import java.util.List;

public interface TicketDao {
    List<Ticket> getTicketsByUserId(int id);
    void createTicket(Ticket ticket);
    void deleteTicketsBySeanceId(int id);
}
