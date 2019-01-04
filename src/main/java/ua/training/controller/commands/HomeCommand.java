package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommand extends Command {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        forward("/index.jsp");
    }
}
