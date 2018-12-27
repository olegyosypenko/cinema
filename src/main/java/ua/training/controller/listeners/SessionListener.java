package ua.training.controller.listeners;

import org.apache.log4j.Logger;
import ua.training.model.entity.Role;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final Logger logger = Logger.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        logger.info("Session created: " + session.getId());
        session.setAttribute("lang", "uk");
        session.setAttribute("role", Role.UNKNOWN);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        logger.info("Session destroyed: " + session.getId());
    }
}
