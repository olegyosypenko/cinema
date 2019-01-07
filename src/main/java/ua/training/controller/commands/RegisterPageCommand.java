package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class RegisterPageCommand extends Command {

    @Override
    public String process(HttpServletRequest request) {
        return "/WEB-INF/pages/register-page.jsp";
    }
}
