package ua.training.controller.commands;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileCommand extends Command {
    private UserService userService = new UserService();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        int money = userService.getMoneyAmountById(user.getId());
        user.setMoney(money);
        forward("/WEB-INF/pages/profile.jsp");
    }
}
