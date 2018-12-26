package ua.training.controller.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class CreateSeancePageCommand extends Command {
    @Override
    public void process() throws ServletException, IOException {
        forward("/WEB-INF/pages/create-seance.jsp");
    }
}
