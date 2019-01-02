package ua.training.controller.commands;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    Logger logger = Logger.getLogger(Command.class);
    private ServletContext context;
    private HttpServletRequest request;
    private HttpServletResponse response;
    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.request = servletRequest;
        this.context = servletContext;
        this.response = servletResponse;
    }
    public abstract void process(HttpServletRequest request);

    void forward(String target) {
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("ServletException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

    void sendRedirect(String target) {
        try {
            response.sendRedirect(context.getContextPath() + "/servlet/" + target);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

}
