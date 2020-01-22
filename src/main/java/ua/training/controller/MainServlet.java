package ua.training.controller;

import org.apache.log4j.Logger;
import ua.training.controller.commands.*;
import ua.training.controller.util.UriParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MainServlet", urlPatterns = {"/cinema/*"})
public class MainServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MainServlet.class);
    private Map<String, Command> map = new HashMap<>();

    @Override
    public void init() {
        map.put("free/home", new HomeCommand());
        map.put("guest/login-page", new CommonPageCommand(Constants.LOGIN_PAGE));
        map.put("guest/register-page", new CommonPageCommand(Constants.REGISTER_PAGE));
        map.put("guest/login", new LoginCommand());
        map.put("guest/register", new RegisterCommand());
        map.put("logged/logout", new LogoutCommand());
        map.put("admin/create-film-page", new CommonPageCommand(Constants.CREATE_FILM_PAGE));
        map.put("admin/create-film", new CreateFilmCommand());
        map.put("admin/create-seance-page", new CreateSeancePageCommand());
        map.put("admin/create-seance", new CreateSeanceCommand());
        map.put("free/films", new ShowFilmsCommand());
        map.put("free/schedule", new ShowSeancesByDateCommand());
        map.put("user/profile", new ProfileCommand());
        map.put("user/add-money", new AddMoneyCommand());
        map.put("free/buy-tickets-page", new BuyTicketsPageCommand());
        map.put("user/buy-tickets", new BuyTicketsCommand());
        map.put("user/your-tickets", new ShowTicketsByUserCommand());
        map.put("free/film", new ShowFilmCommand());
        map.put("admin/delete-seance", new DeleteSeanceCommand());
        map.put("admin/delete-film", new DeleteFilmCommand());
        map.put("user/add-money-page", new CommonPageCommand(Constants.ADD_MONEY_PAGE));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = UriParser.getCommandNameFromUri(request.getRequestURI());
        ServletContext context = request.getServletContext();
        logger.info("Command name: " + commandName);
        Command command = map.get(commandName);
        if (command == null) {
            response.sendError(404, "Page not found");
        } else {
            String url = command.process(request);
            if (url.contains("redirect:")) {
                url = url.replace("redirect:", "");
                response.sendRedirect(context.getContextPath() + "/cinema/" + url);
            }
            else {
                RequestDispatcher dispatcher = context.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        }
}
}
