package ua.training.controller.commands;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {
    Logger logger = Logger.getLogger(LogoutCommand.class);
    @Override
    public String process(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        logger.info("Invalidate session invoked!");
        httpSession.invalidate();
        return "redirect:free/home?logout=true";
    }
}
