package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginCommand extends Command {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void process() throws IOException {
        UserService userService = new UserService();
        User user;
        try {
            user = userService.getUserByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
        } catch (Exception e) {
            logger.error("Incorrect password or username", e);
            sendRedirect("login-page");
            return;
        }
        if (isLoggedIn(user)) {
            logger.info("UserDto is already logged in");
            sendRedirect("login-page");
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        sendRedirect("welcome");
    }

    private boolean isLoggedIn(final User newUser) {
        @SuppressWarnings("unchecked")
        List<User> loggedUsers = (List<User>) context.getAttribute("logged-users");
        if (loggedUsers.stream().anyMatch(user -> user.equals(newUser))) {
            return true;
        }
        loggedUsers.add(newUser);
        return false;
    }

}
