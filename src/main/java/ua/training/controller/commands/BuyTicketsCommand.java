package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.entity.Seance;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;
import ua.training.model.service.SeanceService;
import ua.training.model.service.ServiceException;
import ua.training.model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BuyTicketsCommand extends Command {
    private Logger logger = Logger.getLogger(BuyTicketsCommand.class);
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        int seanceId = Integer.parseInt(request.getParameter("seance-id"));
        logger.trace("process start");
        HttpSession httpSession = request.getSession();
        TicketService ticketService = new TicketService();
        try {
            List<Ticket> tickets = new ArrayList<>();
            String[] seatParameters = request.getParameterValues("seat");
            String[] rowParameters = request.getParameterValues("row");

            User user = (User) httpSession.getAttribute("user");
            if (seatParameters == null || rowParameters == null
                    || seatParameters.length == 0 || seatParameters.length != rowParameters.length) {
                sendRedirect("free/buy-tickets-page/" + seanceId + "?error=no-tickets-chosen");
                return;
            }
            for (int i = 0; i < seatParameters.length; i++) {
                Ticket ticket = new Ticket();
                Seance seance = new Seance();
                seance.setId(seanceId);
                int seat = Integer.parseInt(seatParameters[i]);
                int row = Integer.parseInt(rowParameters[i]);
                ticket.setSeat(seat);
                ticket.setRow(row);
                ticket.setSeance(seance);
                ticket.setUser(user);
                tickets.add(ticket);
            }
            ticketService.createTickets(tickets);
            sendRedirect("free/buy-tickets-page/" + seanceId + "?success=tickets-bought");
        } catch (DaoException e) {
            logger.error("Cannot create tickets", e);
            sendRedirect("free/schedule?error=seance-not-exists");
        } catch (ServiceException e) {
            logger.error("Cannot create tickets", e);
            sendRedirect("free/buy-tickets-page/" + seanceId + "?error=cannot-buy-tickets");
        }
    }
}