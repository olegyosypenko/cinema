package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class CreateFilmPageCommand extends Command {
    @Override
    public void process(HttpServletRequest request) {
        forward("/WEB-INF/pages/create-film.jsp");
    }
}
