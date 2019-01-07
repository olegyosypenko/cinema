package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class LoginPageCommand extends Command {
    @Override
    public String process(HttpServletRequest request) {
        return "/WEB-INF/pages/login-page.jsp";
    }
}
