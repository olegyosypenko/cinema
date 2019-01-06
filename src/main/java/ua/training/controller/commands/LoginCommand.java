package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginCommand extends Command {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    private UserService userService = new UserService();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        ServletContext context = request.getServletContext();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null || username.length() < 4 || password.length() < 4 ||
                username.length() > 14 || password.length() > 14) {

            sendRedirect("guest/login-page?error=incorrect-login-input");
            return;
        }

        User user;
        try {
            user = userService.getUserByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
        } catch (Exception e) {
            logger.error("Incorrect password or username", e);
            sendRedirect("guest/login-page?error=incorrect-username-password");
            return;
        }
        @SuppressWarnings("unchecked")
        List<String> loggedUsers = (List<String>) context.getAttribute("logged-users");
        logger.debug("Logged users: " + loggedUsers);
        if (loggedUsers.contains(user.getUsername())) {
            logger.info("User is already logged in");
            sendRedirect("guest/login-page?error=user-already-logged");
            return;
        }
        loggedUsers.add(user.getUsername());
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        sendRedirect("free/home?login=true");
    }
}
