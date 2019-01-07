package ua.training.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger logger = Logger.getLogger(EncodingFilter.class);
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.trace("EncodingFilter");
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
