package ua.training.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HomeCommand extends Command {

    @Override
    public void process(HttpServletRequest request) {
        forward("/index.jsp");
    }
}
