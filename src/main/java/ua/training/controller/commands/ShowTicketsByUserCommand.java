package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;
import ua.training.model.service.TicketService;

import java.util.List;

public class ShowTicketsByUserCommand extends Command {
    private Logger logger = Logger.getLogger(ShowTicketsByUserCommand.class);
    @Override
    public void process() {
        User user = (User) httpSession.getAttribute("user");
        TicketService ticketService = new TicketService();
        List<Ticket> tickets = ticketService.getTicketsByUserId(user.getId());
        logger.info("Number of tickets: " + tickets.size());
        request.setAttribute("tickets", tickets);
        forward("/WEB-INF/pages/your-tickets.jsp");
    }
}
