package ua.training.controller.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class Command {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession httpSession;
    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
        this.httpSession = servletRequest.getSession();
    }
    public void defaultProcess() throws ServletException, IOException {
        if (!isAccessAllowed()) {
            sendRedirect("home");
            return;
        }
        process();
    }
    public abstract void process() throws ServletException, IOException;

    protected void forward(String target) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }

    protected void sendRedirect(String target) throws IOException {
        response.sendRedirect(context.getContextPath() + "/servlet/" + target);
    }

    public abstract boolean isAccessAllowed();
}
