package ua.training.controller;

import org.apache.log4j.Logger;
import ua.training.controller.commands.*;

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
    public void init() {
        map.put("home", new HomeCommand());
        map.put("welcome", new WelcomeCommand());
        map.put("login-page", new LoginPageCommand());
        map.put("register-page", new RegisterPageCommand());
        map.put("login", new LoginCommand());
        map.put("register", new RegisterCommand());
        map.put("logout", new LogoutCommand());
        map.put("goodbye", new GoodbyeCommand());
        map.put("lang", new ChangeLanguageCommand());
        map.put("create-film-page", new CreateFilmPageCommand());
        map.put("create-film", new CreateFilmCommand());
        map.put("films", new DisplayAllFilmsCommand());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        String commandName = (String) request.getAttribute("command");
        Command command = map.get(commandName);
        command.init(request.getServletContext(), request, response);
        command.process();

    }
}
