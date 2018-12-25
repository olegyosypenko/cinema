package ua.training.controller.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class ChangeLanguageCommand extends Command {
    @Override
    public void process() throws IOException {
        httpSession.setAttribute("lang", request.getParameter("lang"));
        sendRedirect("home");
    }
}
