package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.entity.Seance;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;
import ua.training.model.service.ServiceException;
import ua.training.model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class BuyTicketsCommand extends Command {
    private TicketService ticketService = new TicketService();
    private Logger logger = Logger.getLogger(BuyTicketsCommand.class);
    @Override
    public String process(HttpServletRequest request) {
        logger.trace("process start");
        int seanceId = Integer.parseInt(request.getParameter("seance-id"));
        try {
            if (!isCorrectInput(request)) {
                return "redirect:free/buy-tickets-page/" + seanceId + "?error=no-tickets-chosen";
            }
            ticketService.createTickets(getTicketsFromParameters(request));
            return "redirect:free/buy-tickets-page/" + seanceId + "?success=tickets-bought";
        } catch (DaoException e) {
            logger.error("Cannot create tickets", e);
            return "redirect:free/schedule?error=seance-not-exists";
        } catch (ServiceException e) {
            logger.error("Cannot create tickets", e);
            return "redirect:free/buy-tickets-page/" + seanceId + "?error=cannot-buy-tickets";
        }
    }

    private boolean isCorrectInput(HttpServletRequest request) {
        String seanceIdParam = request.getParameter("seance-id");
        String priceParam = request.getParameter("price");
        String[] seatParameters = request.getParameterValues("seat");
        String[] rowParameters = request.getParameterValues("row");
        if (seanceIdParam == null || priceParam == null || seatParameters == null || rowParameters == null
                || rowParameters.length == 0 || seatParameters.length != rowParameters.length) {
            return false;
        }
        try {
            Integer.parseInt(seanceIdParam);
            Integer.parseInt(priceParam);
            for (String seat : seatParameters) {
                Integer.parseInt(seat);
            }
            for (String row : rowParameters) {
                Integer.parseInt(row);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private List<Ticket> getTicketsFromParameters(HttpServletRequest request) {
        int seanceId = Integer.parseInt(request.getParameter("seance-id"));
        int price = Integer.parseInt(request.getParameter("price"));
        List<Ticket> tickets = new ArrayList<>();
        String[] seatParameters = request.getParameterValues("seat");
        String[] rowParameters = request.getParameterValues("row");
        User user = (User) request.getSession().getAttribute("user");
        for (int i = 0; i < seatParameters.length; i++) {
            Ticket ticket = new Ticket();
            Seance seance = new Seance();
            seance.setPrice(price);
            seance.setId(seanceId);
            int seat = Integer.parseInt(seatParameters[i]);
            int row = Integer.parseInt(rowParameters[i]);
            ticket.setSeat(seat);
            ticket.setRow(row);
            ticket.setSeance(seance);
            ticket.setUser(user);
            tickets.add(ticket);
        }
        return tickets;
    }
}