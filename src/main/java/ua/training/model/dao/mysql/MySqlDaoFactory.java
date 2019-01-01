package ua.training.model.dao.mysql;

import ua.training.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDaoFactory extends DaoFactory {
    DataSource dataSource = ConnectionPool.getDataSource();

    public MySqlDaoFactory() {}

    @Override
    public FilmDao createFilmDao() {
        return new FilmMySqlDao(getConnection());
    }

    @Override
    public SeanceDao createSeanceDao() {
        return new SeanceMySqlDao(getConnection());
    }


    @Override
    public UserDao createUserDao() {
        return new UserMySqlDao(getConnection());
    }
    @Override
    public TicketDao createTicketDao() {
        return new TicketMySqlDao(getConnection());
    }
    @Override
    public HallDao createHallDao() {
        return new HallMySqlDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
