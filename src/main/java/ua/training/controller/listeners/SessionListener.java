package ua.training.controller.listeners;

import org.apache.log4j.Logger;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

public class SessionListener implements HttpSessionListener {
    private static final Logger logger = Logger.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        logger.info("Session created: " + session.getId());
        session.setAttribute("lang", "uk");
        User unknownUser = new User();
        unknownUser.setRole(Role.UNKNOWN);
        session.setAttribute("user", unknownUser);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        User user = (User) session.getAttribute("user");
        ServletContext context = session.getServletContext();
        List<User> users = (List<User>) context.getAttribute("logged-users");
        users.remove(user);
        logger.info("Session destroyed: " + session.getId());
    }

}
