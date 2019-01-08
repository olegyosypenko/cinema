package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.CalendarUtil;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.entity.Film;
import ua.training.model.entity.Seance;
import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateSeanceCommand extends Command {
    private Logger logger = Logger.getLogger(CreateSeanceCommand.class);
    private SeanceService seanceService = new SeanceService();
    @Override
    public String process(HttpServletRequest request) {
        logger.trace("process");
        if (!isCorrectInput(request)) {
            return "redirect:admin/create-seance-page?error=incorrect-input";
        }
        try {
            seanceService.createSeance(getSeanceFromInput(request));
        } catch (DaoException daoException) {
            return "redirect:admin/create-seance-page?error=cannot-create-seance";
        }
        return "redirect:admin/create-seance-page?success=seance-created";
    }

    private boolean isCorrectInput(HttpServletRequest request) {
        String priceParam = request.getParameter("price");
        String idParam = request.getParameter("film-id");
        String startTimeParam = request.getParameter("start-time");
        if (priceParam == null || idParam == null || startTimeParam == null) {
            return false;
        }
        int price;
        int filmId;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            price = Integer.parseInt(request.getParameter("price"));
            filmId = Integer.parseInt(request.getParameter("film-id"));
            if (price < 0 || filmId < 0) {
                logger.debug("price and filmId should not be less than zero");
                return false;
            }
            Timestamp startTime = new Timestamp(formatter.parse(startTimeParam).getTime());
            if (startTime.before(new Timestamp(System.currentTimeMillis()))) {
                logger.debug("startTime is in past!");
                return false;
            }
        } catch (NumberFormatException | ParseException e) {
            logger.error("Parse exception!", e);
            return false;
        }
        return true;
    }

    private Seance getSeanceFromInput(HttpServletRequest request) {
        int price = Integer.parseInt(request.getParameter("price"));
        int filmId = Integer.parseInt(request.getParameter("film-id"));
        Film film = new Film();
        film.setId(filmId);
        Seance seance = new Seance();
        seance.setFilm(film);
        seance.setPrice(price);
        seance.setStartTime(CalendarUtil.getTimestampFromString(request.getParameter("start-time")));
        return seance;
    }
}
