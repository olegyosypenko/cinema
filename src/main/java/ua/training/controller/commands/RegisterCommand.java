package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.UserDto;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand extends Command {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(RegisterCommand.class);
    private UserService userService = new UserService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String firstNameEN = request.getParameter("first-name-en");
        String lastNameEN = request.getParameter("last-name-en");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null || username.length() < 4 || password.length() < 4 ||
                username.length() > 14 || password.length() > 14) {
            sendRedirect("guest/register-page?error=incorrect-login-input");
            return;
        }

        UserDto user = new UserDto();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setFirstNameEN(firstNameEN);
        user.setLastNameEN(lastNameEN);
        user.setUsername(username);
        user.setPassword(password);
        try {
            userService.createUser(user);
        } catch (NotUniqueValueException e) {
            logger.error("Username is taken exception", e);
            sendRedirect("guest/register-page?error=username-taken");
            return;
        }
        forward("/servlet/guest/login");
    }
}
