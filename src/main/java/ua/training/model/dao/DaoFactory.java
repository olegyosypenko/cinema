package ua.training.model.dao;

public interface DaoFactory {
    FilmDao createFilmDao();
    SeanceDao createSeanceDao();
    TicketDao createTicketDao();
    UserDao createUserDao();
}
