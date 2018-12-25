package ua.training.controller.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class HomeCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        forward("/index.jsp");
    }
}
