package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.MainServlet;
import ua.training.controller.commands.*;
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
    }
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("AuthFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String commandName = request.getRequestURI().replace(request.getContextPath() + "/servlet/", "");
        commandName = commandName.split("/")[0];

        Role role = ((Role) request.getSession().getAttribute("role"));

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