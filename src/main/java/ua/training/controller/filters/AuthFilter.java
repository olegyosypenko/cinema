package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.util.UriParser;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthFilter implements Filter {
    private final Logger logger = Logger.getLogger(AuthFilter.class);
    private Map<String, List<Role>> map;

    @Override
    public void init(FilterConfig filterConfig) {
        map = new HashMap<>();
        map.put("user", Collections.singletonList(Role.USER));
        map.put("admin", Collections.singletonList(Role.ADMIN));
        map.put("guest", Collections.singletonList(Role.UNKNOWN));
        map.put("logged", Arrays.asList(Role.ADMIN, Role.USER));
        map.put("free", Arrays.asList(Role.ADMIN, Role.USER, Role.UNKNOWN));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        String uri = request.getRequestURI();
        String accessLabel = UriParser.getAccessLabel(uri);
        List<Role> roles = map.get(accessLabel);
        logger.debug("URI: " + uri);
        logger.debug("accessLabel: " + accessLabel);
        logger.debug("user role: " + user.getRole());
        if (roles == null) {
            response.sendError(404, "Page not found");
        } else if (roles.contains(user.getRole())) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            response.sendError(403, "access.forbidden.label");
        }
    }



    @Override
    public void destroy() {

    }
}