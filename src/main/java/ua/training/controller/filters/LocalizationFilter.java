package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.MainServlet;
import ua.training.model.BundlePool;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LocalizationFilter.class);
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("LocalizationFilter");
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        if (servletRequest.getParameter("lang") != null) {
            session.setAttribute("lang", servletRequest.getParameter("lang"));
        }
        Locale locale = new Locale((String) session.getAttribute("lang"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("mysql_localized", locale);
        BundlePool.putBundle(resourceBundle);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
