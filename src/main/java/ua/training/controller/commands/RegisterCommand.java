package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.RegexUtil;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.UserDto;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand extends Command {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(RegisterCommand.class);
    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request) {
        UserDto userFromInput = createUserFromInput(request);
        if (!validateUser(userFromInput)) {
            logger.info("Incorrect input");
            return "redirect:guest/register-page?error=incorrect-register-input";
        }
        try {
            userService.createUser(userFromInput);
        } catch (NotUniqueValueException e) {
            logger.error("Username is taken exception", e);
            return "redirect:guest/register-page?error=username-taken";
        }
        return "/cinema/guest/login";
    }

    private boolean validateUser(UserDto userDto) {
        return RegexUtil.matches("first.name.regex", userDto.getFirstName())
                && RegexUtil.matches("last.name.regex", userDto.getLastName())
                && RegexUtil.matches("first.name.en.regex", userDto.getFirstNameEN())
                && RegexUtil.matches("last.name.en.regex", userDto.getLastNameEN())
                && RegexUtil.matches("login.regex", userDto.getUsername())
                && RegexUtil.matches("password.regex", userDto.getPassword());
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
