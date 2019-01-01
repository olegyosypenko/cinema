package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import java.io.IOException;

public class AddMoneyCommand extends Command {
    private static final Logger logger = Logger.getLogger(AddMoneyCommand.class);
    @Override
    public void process() throws IOException {
        logger.info("Parameter money: " + request.getParameter("money"));
        UserService userService = new UserService();
        try {
            User user = (User) httpSession.getAttribute("user");
            int money = Integer.parseInt(request.getParameter("money"));
            userService.addMoneyByUsername(user.getUsername(), money);
            sendRedirect("home");
        } catch (NumberFormatException e) {
            logger.info("Not a number: " + Integer.parseInt(request.getParameter("money")), e);
            sendRedirect("home");
        }
    }
}
