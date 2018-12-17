package ua.training.controller.command;

import org.apache.log4j.Logger;
import ua.training.controller.listeners.SessionListener;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginCommand extends Command {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        UserService userService = new UserService();
        User user;
        try {
            user = userService.getUserByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
        } catch (Exception e) {
            logger.error("Incorrect password or username", e);
            sendRedirect("login-page");
            return;
        }
        List<User> loggedUsers = (List<User>) context.getAttribute("logged-users");
        loggedUsers.add(user);
        HttpSession session = request.getSession();
        session.setAttribute("role", user.getRole());
        session.setAttribute("username", request.getParameter("username"));
        sendRedirect("welcome");
    }

    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role").equals(User.Role.UNKNOWN);
    }

}
