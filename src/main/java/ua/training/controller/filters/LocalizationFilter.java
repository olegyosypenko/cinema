package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.MainServlet;
import ua.training.model.BundlePool;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationFilter implements Filter {
    private static final Logger logger = Logger.getLogger(MainServlet.class);
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("LocalizationFilter");
        Locale locale = new Locale((String) ((HttpServletRequest) servletRequest).getSession().getAttribute("lang"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("mysql_localized", locale);
        BundlePool.instance.putBundle(Thread.currentThread().getName(), resourceBundle);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
