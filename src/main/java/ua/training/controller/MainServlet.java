package ua.training.controller;

import org.apache.log4j.Logger;
import ua.training.controller.command.*;
import ua.training.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "MainServlet", urlPatterns = {"/servlet/*"})
public class MainServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MainServlet.class);
    Map<String, Command> map = new HashMap<>();

    @Override
    public void init() throws ServletException {
        List<User> loggedUsers = new ArrayList<>();
        getServletContext().setAttribute("logged-users", loggedUsers);
        map.put("home", new HomeCommand());
        map.put("welcome", new WelcomeCommand());
        map.put("login-page", new LoginPageCommand());
        map.put("register-page", new RegisterPageCommand());
        map.put("login", new LoginCommand());
        map.put("register", new RegisterCommand());
        map.put("logout", new LogoutCommand());
        map.put("goodbye", new GoodbyeCommand());
        map.put("lang", new ChangeLanguageCommand());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        Command command = getCommand(request);
        command.init(request.getServletContext(), request, response);
        command.defaultProcess();

    }

    private Command getCommand(HttpServletRequest request) {
        logger.info("request URI: " + request.getRequestURI());
        String commandName = request.getRequestURI().replace(request.getContextPath() + "/servlet/", "");
        if (commandName.equals("/cinema/")) commandName = "home";
        logger.info("command name: " + commandName);
        Command result = map.get(commandName);
        return result;
    }
}
