package ua.training.controller.commands;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class Command {
    Logger logger = Logger.getLogger(Command.class);
    ServletContext context;
    HttpServletRequest request;
    private HttpServletResponse response;
    HttpSession httpSession;
    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
        this.httpSession = servletRequest.getSession();
    }
    public abstract void process() throws ServletException, IOException;

    protected void forward(String target) {
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("ServletException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

    protected void sendRedirect(String target) {
        try {
            response.sendRedirect(context.getContextPath() + "/servlet/" + target);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

}
