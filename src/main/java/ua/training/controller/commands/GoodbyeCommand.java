package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class GoodbyeCommand extends Command {
    @Override
    public void process(HttpServletRequest request) {
        forward("/WEB-INF/pages/goodbye.jsp");
    }
}
