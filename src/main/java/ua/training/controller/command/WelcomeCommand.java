package ua.training.controller.command;

import javax.servlet.ServletException;
import java.io.IOException;

public class WelcomeCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        if (!isAccessAllowed()) {
            sendRedirect("home");
        }
        forward("welcome.jsp");
    }

    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role") != null && (httpSession.getAttribute("role").equals("ADMIN") ||
                httpSession.getAttribute("role").equals("USER"));
    }

}
