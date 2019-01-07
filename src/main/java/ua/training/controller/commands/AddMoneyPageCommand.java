package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class AddMoneyPageCommand extends Command {

    @Override
    public String process(HttpServletRequest request) {
        return "/WEB-INF/pages/add-money-page.jsp";
    }
}
