package ua.training.controller.command;

import javax.servlet.ServletException;
import java.io.IOException;

public class GoodbyeCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        if (!isAccessAllowed()) {
            sendRedirect("home");
            return;
        }
        forward("goodbye.jsp");
    }

    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role") == null || httpSession.getAttribute("role").equals("UNKNOWN");
    }
}
