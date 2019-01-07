package ua.training.controller.commands;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand extends Command {
    private UserService userService = new UserService();
    @Override
    public String process(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", userService.getUserById(user.getId()));
        return "/WEB-INF/pages/profile.jsp";
    }
}
