package ua.training.model.dao.mysql;

import ua.training.model.dao.*;

public class MySqlDaoFactory implements DaoFactory {

    @Override
    public FilmDao createFilmDao() {
        return new FilmMySqlDao();
    }

    @Override
    public SeanceDao createSeanceDao() {
        return new SeanceMySqlDao();
    }

    @Override
    public TicketDao createTicketDao() {
        return new TicketMySqlDao();
    }

    @Override
    public UserDao createUserDao() {
        return new UserMySqlDao();
    }
}
