package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;
import ua.training.model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTicketsByUserCommand extends Command {
    private Logger logger = Logger.getLogger(ShowTicketsByUserCommand.class);
    private TicketService ticketService = new TicketService();
    @Override
    public String process(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        List<Ticket> tickets = ticketService.getTicketsByUserId(user.getId());
        logger.info("Number of tickets: " + tickets.size());
        request.setAttribute("tickets", tickets);
        return "/WEB-INF/pages/your-tickets.jsp";
    }
}
