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
    public String process(HttpServletRequest request, HttpServletResponse response) {
        if (!isInputCorrect(request)) {
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
        if (username == null || password == null
                || username.length() < Constants.USERNAME_MIN_LENGTH
                || password.length() < Constants.PASSWORD_MIN_LENGTH
                || username.length() > Constants.USERNAME_MAX_LENGTH
                || password.length() > Constants.PASSWORD_MAX_LENGTH) {
            return false;
        }
        if (firstName != null && firstName.length() > Constants.FIRST_NAME_MAX_LENGTH) {
            return false;
        }
        if (lastName != null && lastName.length() > Constants.FIRST_NAME_MAX_LENGTH) {
            return false;
        }
        if (firstNameEN != null && firstNameEN.length() > Constants.LAST_NAME_MAX_LENGTH) {
            return false;
        }
        return lastNameEN == null || lastNameEN.length() <= Constants.LAST_NAME_MAX_LENGTH;
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
