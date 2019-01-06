package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.UriParser;
import ua.training.model.entity.Film;
import ua.training.model.service.FilmService;
import ua.training.model.service.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ResourceBundle;

public class ShowFilmsCommand extends Command {
    private Logger logger = Logger.getLogger(ShowFilmsCommand.class);
    private FilmService filmService = new FilmService();
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        List<Film> films = filmService.getAllFilms();
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        int numberOfItems = Integer.parseInt(bundle.getString("items.per.page"));
        Paginator<Film> paginator = new Paginator<>(films, numberOfItems);
        int index = 1;
        try {
            index = UriParser.getIndexFromUri(request.getRequestURI());
            logger.debug("Page index: " + index);
        } catch(NumberFormatException number) {
            logger.debug("Incorrect number");
        }
        request.setAttribute("films", paginator.getFilmsPart(index));
        request.setAttribute("indexes", paginator.getIndexes());
        request.setAttribute("currentIndex", paginator.getCurrentIndex());
        request.setAttribute("previousIndex", paginator.getPreviousIndex());
        request.setAttribute("nextIndex", paginator.getNextIndex());
        return "/WEB-INF/pages/display-films.jsp";
    }
}
