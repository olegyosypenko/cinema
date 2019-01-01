package ua.training.controller.commands;

import ua.training.model.entity.User;

import java.io.IOException;
import java.util.List;

public class LogoutCommand extends Command {

    @Override
    public void process() throws IOException {
        List<User> loggedUsers = (List<User>) context.getAttribute("logged-users");
        User user = (User) httpSession.getAttribute("user");
        for (int i = 0; i < loggedUsers.size(); i++) {
            if (loggedUsers.get(i).getUsername().equals(user.getUsername())) {
                loggedUsers.remove(i);
                i--;
            }
        }
        context.setAttribute("logged-users", loggedUsers);
        httpSession.invalidate();
        sendRedirect("goodbye");
    }
}
