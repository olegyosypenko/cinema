package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.commands.BuyTicketsCommand;
import ua.training.controller.commands.BuyTicketsPageCommand;
import ua.training.controller.commands.ShowTicketsByUserCommand;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AuthFilter.class);
    private Map<String, List<Role>> map = new HashMap<>();
    {
        map.put("home", Arrays.asList(Role.ADMIN, Role.USER, Role.UNKNOWN));
        map.put("welcome", Arrays.asList(Role.ADMIN, Role.USER));
        map.put("login-page", Collections.singletonList(Role.UNKNOWN));
        map.put("register-page", Collections.singletonList(Role.UNKNOWN));
        map.put("login", Collections.singletonList(Role.UNKNOWN));
        map.put("register", Collections.singletonList(Role.UNKNOWN));
        map.put("logout", Arrays.asList(Role.ADMIN, Role.USER));
        map.put("goodbye", Collections.singletonList(Role.UNKNOWN));
        map.put("create-film-page", Collections.singletonList(Role.ADMIN));
        map.put("create-film", Collections.singletonList(Role.ADMIN));
        map.put("create-seance-page", Collections.singletonList(Role.ADMIN));
        map.put("create-seance", Collections.singletonList(Role.ADMIN));
        map.put("films", Arrays.asList(Role.ADMIN, Role.USER, Role.UNKNOWN));
        map.put("schedule", Arrays.asList(Role.ADMIN, Role.USER, Role.UNKNOWN));
        map.put("profile", Collections.singletonList(Role.USER));
        map.put("add-money", Collections.singletonList(Role.USER));
        map.put("buy-tickets-page", Collections.singletonList(Role.USER));
        map.put("buy-tickets", Collections.singletonList(Role.USER));
        map.put("your-tickets", Collections.singletonList(Role.USER));
        map.put("film", Arrays.asList(Role.ADMIN, Role.USER, Role.UNKNOWN));
    }
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uriWithoutContextPath = request.getRequestURI().replace(request.getContextPath(), "");
        String commandName = uriWithoutContextPath.split("/")[2];
        logger.info("Command name: " + commandName);
        User user = ((User) request.getSession().getAttribute("user"));
        Role role = user.getRole();
        if (map.get(commandName) != null && map.get(commandName).contains(role)) {
            request.setAttribute("command", commandName);
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(request.getContextPath() + "/servlet/home");
        }
    }

    @Override
    public void destroy() {

    }
}