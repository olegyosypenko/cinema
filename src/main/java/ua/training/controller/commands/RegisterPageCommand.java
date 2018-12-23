package ua.training.controller.commands;

import ua.training.model.entity.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegisterPageCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        forward("/WEB-INF/pages/register-page.jsp");
    }
    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role").equals(User.Role.UNKNOWN);
    }
}
