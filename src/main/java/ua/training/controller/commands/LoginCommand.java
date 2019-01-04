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
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        ServletContext context = request.getServletContext();

        User user;
        try (UserService userService = new UserService()) {
            user = userService.getUserByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
        } catch (Exception e) {
            logger.error("Incorrect password or username", e);
            sendRedirect("guest/login-page");
            return;
        }
        @SuppressWarnings("unchecked")
        List<User> loggedUsers = (List<User>) context.getAttribute("logged-users");
        if (loggedUsers.contains(user)) {
            logger.info("User is already logged in");
            sendRedirect("guest/login-page");
            return;
        }
        loggedUsers.add(user);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        Cookie userCookie = new Cookie("new-user", "1");
        userCookie.setMaxAge(60); //Store cookie for 1 year
        response.addCookie(userCookie);
        sendRedirect("free/home");
    }
}
