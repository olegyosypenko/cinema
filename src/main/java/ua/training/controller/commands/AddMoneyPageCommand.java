package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class AddMoneyPageCommand extends Command {

    @Override
    public void process(HttpServletRequest request) {
        forward("/WEB-INF/pages/add-money-page.jsp");
    }
}
