package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateFilmPageCommand extends Command {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/pages/create-film.jsp";
    }
}
