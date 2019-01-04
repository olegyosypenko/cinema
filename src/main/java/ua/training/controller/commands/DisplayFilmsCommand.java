package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.UriParser;
import ua.training.model.entity.Film;
import ua.training.model.service.FilmService;
import ua.training.model.service.Paginator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DisplayFilmsCommand extends Command {
    private Logger logger = Logger.getLogger(DisplayFilmsCommand.class);
    @Override
    public void process(HttpServletRequest request) {
        try (FilmService filmService = new FilmService()) {
            List<Film> films = filmService.getAllFilms();
            int numberOfItems = 5;
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
            forward("/WEB-INF/pages/display-films.jsp");
        }
    }
}
