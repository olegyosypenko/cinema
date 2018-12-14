package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegisterCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        if (!isAccessAllowed()) {
            sendRedirect("home");
            return;
        }
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(username);
        user.setPassword(password);
        user.setRole(User.Role.USER);
        user.setMoney(0);
        UserService userService = new UserService();
        try {
            userService.createUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        forward("/servlet/login");
    }
    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role") == null || httpSession.getAttribute("role").equals("UNKNOWN");
    }
}
