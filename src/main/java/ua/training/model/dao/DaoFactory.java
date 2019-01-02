package ua.training.model.dao;

import ua.training.model.dao.mysql.MySqlDaoFactory;

public abstract class DaoFactory {

    public abstract HallDao createHallDao();
    public abstract FilmDao createFilmDao();
    public abstract SeanceDao createSeanceDao();
    public abstract TicketDao createTicketDao();
    public abstract UserDao createUserDao();
    public abstract Transaction getTransaction();
    public static DaoFactory getInstance(){
        return new MySqlDaoFactory();
    }
}
