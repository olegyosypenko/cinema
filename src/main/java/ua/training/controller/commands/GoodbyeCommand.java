package ua.training.controller.commands;

import ua.training.model.entity.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class GoodbyeCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        forward("/WEB-INF/pages/goodbye.jsp");
    }
}
