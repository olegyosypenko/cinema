package ua.training.model.dao;

import ua.training.model.entity.Ticket;

import java.util.List;

public interface TicketDao extends AutoCloseable {
    List<Ticket> getTicketsByUserId(int id);
    void createTicket(Ticket ticket);
    void deleteTicketsBySeanceId(int id);

    List<Ticket> getTicketsBySeanceId(int id);
    void createTickets(List<Ticket> tickets);
    void close();

}
