package ua.training.controller.command;

import ua.training.model.entity.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoginPageCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        forward("/WEB-INF/pages/login-page.jsp");
    }
    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role").equals(User.Role.UNKNOWN);
    }

}
