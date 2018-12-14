package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        if (!isAccessAllowed()) {
            sendRedirect("home");
            return;
        }
        UserService userService = new UserService();
        User user = null;
        System.out.println("check1");
        try {
            user = userService.getUserByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("check2");
        if (user == null) {
            sendRedirect("login");
            return;
        }
        List<User> loggedUsers = (List<User>) context.getAttribute("logged-users");
        loggedUsers.add(user);
        System.out.println("check3");
        for (int i = 0; i < loggedUsers.size(); i++) {
            System.out.println(loggedUsers.get(i));
        }
        System.out.println("check4");
        HttpSession session = request.getSession();
        session.setAttribute("role", user.getRole().name());
        session.setAttribute("username", request.getParameter("username"));
        sendRedirect("welcome");
    }

    @Override
    public boolean isAccessAllowed() {
        return httpSession.getAttribute("role") == null || httpSession.getAttribute("role").equals("UNKNOWN");
    }

}
