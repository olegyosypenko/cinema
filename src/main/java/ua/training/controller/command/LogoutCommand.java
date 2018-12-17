package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LogoutCommand extends Command {

    @Override
    public void process() throws IOException {
        List<User> loggedUsers = (List<User>) context.getAttribute("logged-users");
        for (int i = 0; i < loggedUsers.size(); i++) {
            if (loggedUsers.get(i).getUsername().equals(httpSession.getAttribute("username"))) {
                loggedUsers.remove(i);
                i--;
            }
        }
        httpSession.invalidate();
        sendRedirect("goodbye");
    }

    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role").equals(User.Role.USER)
                || httpSession.getAttribute("role").equals(User.Role.ADMIN);
    }

}
