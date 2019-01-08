package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddMoneyCommand extends Command {
    private static final Logger logger = Logger.getLogger(AddMoneyCommand.class);
    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        logger.trace("Parameter money: " + request.getParameter("money"));
        if (!isCorrectInput(request)) {
            return "redirect:user/add-money-page?error=incorrect-input";
        }
        User user = (User) httpSession.getAttribute("user");
        int money = Integer.parseInt(request.getParameter("money"));
        userService.addMoneyByUserId(user.getId(), money);
        user.setMoney(user.getMoney() + money);
        return "redirect:user/profile?success=money-added";
    }

    private boolean isCorrectInput(HttpServletRequest request) {
        String moneyParam = request.getParameter("money");
        if (moneyParam == null) {
            return false;
        }
        try {
            int money = Integer.parseInt(moneyParam);
            if (money < 0) {
                return false;
            }
        } catch (Exception e) {
            logger.debug("Not a number: " + request.getParameter("money"), e);
            return false;
        }
        return true;
    }
}
