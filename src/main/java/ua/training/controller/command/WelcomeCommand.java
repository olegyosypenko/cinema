package ua.training.controller.command;

import ua.training.model.entity.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class WelcomeCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        forward("/WEB-INF/pages/welcome.jsp");
    }

    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role").equals(User.Role.USER)
                || httpSession.getAttribute("role").equals(User.Role.ADMIN);
    }

}
