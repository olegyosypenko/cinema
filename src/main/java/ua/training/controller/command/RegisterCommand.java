package ua.training.controller.command;

import org.apache.log4j.Logger;
import ua.training.controller.MainServlet;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterCommand extends Command {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(MainServlet.class);

    @Override
    public void process() throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(User.Role.USER);
        user.setMoney(0);
        UserService userService = new UserService();
        try {
            userService.createUser(user);
        } catch (Exception e) {
            logger.error("Username is taken exception", e);
            sendRedirect("register-page");
            return;
        }
        forward("/servlet/login");
    }
    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role").equals(User.Role.UNKNOWN);
    }
}
