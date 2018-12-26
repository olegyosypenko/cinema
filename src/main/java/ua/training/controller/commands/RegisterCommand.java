package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.MainServlet;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegisterCommand extends Command {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(RegisterCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String firstNameEN = request.getParameter("first-name-en");
        String lastNameEN = request.getParameter("last-name-en");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDto user = new UserDto();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setFirstNameEN(firstNameEN);
        user.setLastNameEN(lastNameEN);
        user.setUsername(username);
        user.setPassword(password);
        UserService userService = new UserService();
        try {
            userService.createUser(user);
        } catch (Exception e) {
            logger.error("Username is taken exception", e);
            sendRedirect("register-page");
            return;
        }
        forward("/servlet/login");
    }
}
