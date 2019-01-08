package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginCommand extends Command {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    private UserService userService = new UserService();
    @Override
    public String process(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!isCorrectInput(request)) {
            return "redirect:guest/login-page?error=incorrect-login-input";
        }
        User user;
        try {
            user = userService.getUserByUsernameAndPassword(username, password);
        } catch (DaoException e) {
            logger.error("Incorrect password or username", e);
            return "redirect:guest/login-page?error=incorrect-username-password";
        }
        if (!addUserIfNotExist(request)) {
            logger.info("User is already logged in");
            return "redirect:guest/login-page?error=user-already-logged";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "redirect:free/home?login=true";
    }

    private boolean isCorrectInput(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null) {
            return false;
        }
        if (username.length() < Constants.USERNAME_MIN_LENGTH || username.length() > Constants.USERNAME_MAX_LENGTH) {
            return false;
        }
        return password.length() >= Constants.PASSWORD_MIN_LENGTH && password.length() <= Constants.PASSWORD_MAX_LENGTH;
    }

    private boolean addUserIfNotExist(HttpServletRequest request) {
        ServletContext context = request.getServletContext();
        @SuppressWarnings("unchecked")
        List<String> loggedUsers = (List<String>) context.getAttribute("logged-users");
        logger.debug("Logged users: " + loggedUsers);
        if (loggedUsers.contains(request.getParameter("username"))) {
            logger.info("User is already logged in");
            return false;
        }
        loggedUsers.add(request.getParameter("username"));
        return true;
    }
}
