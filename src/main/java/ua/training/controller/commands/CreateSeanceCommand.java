package ua.training.controller.commands;

import ua.training.model.entity.Film;
import ua.training.model.entity.Seance;
import ua.training.model.service.SeanceService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CreateSeanceCommand extends Command {
    @Override
    public void process() throws IOException {
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
            seance.setStartTime(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        seance.setPrice(price);
        SeanceService seanceService = new SeanceService();
        seanceService.createSeance(seance);
        sendRedirect("home");
    }
}
