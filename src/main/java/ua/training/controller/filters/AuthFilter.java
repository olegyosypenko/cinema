package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.MainServlet;

import javax.servlet.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final Logger logger = Logger.getLogger(MainServlet.class);
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("AuthFilters");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}