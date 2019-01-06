package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPageCommand extends Command {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/pages/register-page.jsp";
    }
}
