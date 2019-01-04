package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.entity.Seance;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;
import ua.training.model.service.SeanceService;
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
        logger.trace("process start");
        HttpSession httpSession = request.getSession();
        try (SeanceService seanceService = new SeanceService(); TicketService ticketService = new TicketService()) {
            List<Ticket> tickets = new ArrayList<>();
            String[] seatParameters = request.getParameterValues("seat");
            String[] rowParameters = request.getParameterValues("row");
            int seanceId = Integer.parseInt(request.getParameter("seance-id"));
            Seance seance = seanceService.getSeanceById(seanceId);

            User user = (User) httpSession.getAttribute("user");
            if (seatParameters.length != rowParameters.length) {
                throw new RuntimeException();
            }
            for (int i = 0; i < seatParameters.length; i++) {
                Ticket ticket = new Ticket();
                int seat = Integer.parseInt(seatParameters[i]);
                int row = Integer.parseInt(rowParameters[i]);
                ticket.setSeat(seat);
                ticket.setRow(row);
                ticket.setSeance(seance);
                ticket.setUser(user);
                tickets.add(ticket);
            }

            ticketService.createTickets(tickets);
            sendRedirect("free/buy-tickets-page/" + seanceId);
        }
    }
}