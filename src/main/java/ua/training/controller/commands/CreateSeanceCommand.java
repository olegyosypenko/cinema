package ua.training.controller.commands;

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
            seance.setStartTime(startTime);
        } catch (ParseException e) {
            sendRedirect("admin/create-seance-page"); //ToDo
        }
        seance.setPrice(price);
        try (SeanceService seanceService = new SeanceService()) {
            seanceService.createSeance(seance);
        }
        sendRedirect("admin/create-seance-page");
    }
}
