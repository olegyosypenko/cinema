package ua.training.controller.listeners;

import ua.training.model.entity.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        List<User> loggedUsers = new ArrayList<>();
        servletContextEvent.getServletContext().setAttribute("logged-users", loggedUsers);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
