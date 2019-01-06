package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand extends Command {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(RegisterCommand.class);
    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        if (isInputCorrect(request)) {
            logger.info("Incorrect input");
            return "redirect:guest/register-page?error=incorrect-login-input";
        }
        try {
            userService.createUser(createUserFromInput(request));
        } catch (NotUniqueValueException e) {
            logger.error("Username is taken exception", e);
            return "redirect:guest/register-page?error=username-taken";
        }
        return "/servlet/guest/login";
    }
    private boolean isInputCorrect(HttpServletRequest request) {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String firstNameEN = request.getParameter("first-name-en");
        String lastNameEN = request.getParameter("last-name-en");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null || username.length() < 4 || password.length() < 4 ||
                username.length() > 14 || password.length() > 14) {
            return false;
        }
        if (firstName != null && firstName.length() > 20) {
            return false;
        }
        if (lastName != null && lastName.length() > 20) {
            return false;
        }
        if (firstNameEN != null && firstNameEN.length() > 20) {
            return false;
        }
        if (lastNameEN != null && lastNameEN.length() > 20) {
            return false;
        }
        return true;
    }
    private UserDto createUserFromInput(HttpServletRequest request) {
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
        return user;
    }
}
