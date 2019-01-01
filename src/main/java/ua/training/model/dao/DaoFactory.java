package ua.training.model.dao;

import ua.training.model.dao.mysql.MySqlDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract HallDao createHallDao();
    public abstract FilmDao createFilmDao();
    public abstract SeanceDao createSeanceDao();
    public abstract TicketDao createTicketDao();
    public abstract UserDao createUserDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory ==null){
                    daoFactory = new MySqlDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
