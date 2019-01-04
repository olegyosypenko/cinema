package ua.training.controller.commands;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {
    Logger logger = Logger.getLogger(LogoutCommand.class);
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        logger.info("Invalidate session invoked!");
        sendRedirect("guest/goodbye");
    }
}
