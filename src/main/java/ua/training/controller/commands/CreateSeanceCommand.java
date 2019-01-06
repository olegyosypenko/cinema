package ua.training.controller.commands;

import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.entity.Film;
import ua.training.model.entity.Seance;
import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateSeanceCommand extends Command {
    private SeanceService seanceService = new SeanceService();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        String startTimeString = request.getParameter("start-time");
        int price = Integer.parseInt(request.getParameter("price"));
        int filmId = Integer.parseInt(request.getParameter("film-id"));
        Film film = new Film();
        film.setId(filmId);
        Seance seance = new Seance();
        seance.setFilm(film);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            Timestamp startTime = new Timestamp(formatter.parse(startTimeString).getTime());
            if (startTime.before(new Timestamp(System.currentTimeMillis()))) {
                sendRedirect("admin/create-seance-page?error=incorrect-input");
                return;
            }
            seance.setStartTime(startTime);
        } catch (ParseException e) {
            sendRedirect("admin/create-seance-page?error=incorrect-input");
            return;
        }
        seance.setPrice(price);
        try {
            seanceService.createSeance(seance);
        } catch (DaoException daoException) {
            sendRedirect("admin/create-seance-page?error=cannot-create-seance");
            return;
        }
        sendRedirect("admin/create-seance-page?success=seance-created");
    }
}
