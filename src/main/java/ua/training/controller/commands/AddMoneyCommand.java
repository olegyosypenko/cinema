package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddMoneyCommand extends Command {
    private static final Logger logger = Logger.getLogger(AddMoneyCommand.class);
    private UserService userService = new UserService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();
        logger.trace("Parameter money: " + request.getParameter("money"));
        try {
            User user = (User) httpSession.getAttribute("user");
            int money = Integer.parseInt(request.getParameter("money"));
            userService.addMoneyByUserId(user.getId(), money);
            user.setMoney(user.getMoney() + money);
            sendRedirect("logged/profile?success=money-added");
        } catch (NumberFormatException e) {
            logger.debug("Not a number: " + request.getParameter("money"), e);
            sendRedirect("user/add-money-page?error=incorrect-input");
        }
    }
}
