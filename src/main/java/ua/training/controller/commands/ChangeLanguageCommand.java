package ua.training.controller.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class ChangeLanguageCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        httpSession.setAttribute("lang", request.getParameter("lang"));
        sendRedirect("home");
    }

    @Override
    public boolean isAccessAllowed() {
        return true;
    }
}
