package ua.training.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ProfileCommand extends Command {
    @Override
    public void process(HttpServletRequest request) {
        forward("/WEB-INF/pages/profile.jsp");
    }
}
