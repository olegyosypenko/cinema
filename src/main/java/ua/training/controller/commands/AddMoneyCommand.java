package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddMoneyCommand extends Command {
    private static final Logger logger = Logger.getLogger(AddMoneyCommand.class);
    @Override
    public void process(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        logger.info("Parameter money: " + request.getParameter("money"));
        try (UserService userService = new UserService()){
            User user = (User) httpSession.getAttribute("user");
            int money = Integer.parseInt(request.getParameter("money"));
            userService.addMoneyByUsername(user.getId(), money);
            sendRedirect("free/home");
        } catch (NumberFormatException e) {
            logger.info("Not a number: " + Integer.parseInt(request.getParameter("money")), e);
            sendRedirect("free/home");
        }
    }
}
