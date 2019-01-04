package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateFilmPageCommand extends Command {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        forward("/WEB-INF/pages/create-film.jsp");
    }
}
