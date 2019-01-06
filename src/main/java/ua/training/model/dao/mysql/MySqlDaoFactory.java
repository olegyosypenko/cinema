package ua.training.model.dao.mysql;

import ua.training.model.dao.*;
import ua.training.model.dao.exceptions.DaoException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPool.getDataSource();
    private Connection connection = getConnection();

    public MySqlDaoFactory() {}

    @Override
    public FilmDao createFilmDao() {
        return new FilmMySqlDao(connection);
    }

    @Override
    public SeanceDao createSeanceDao() {
        return new SeanceMySqlDao(connection);
    }


    @Override
    public UserDao createUserDao() {
        return new UserMySqlDao(connection);
    }

    @Override
    public Transaction getTransaction() {
        return new TransactionMySql(connection);
    }

    @Override
    public TicketDao createTicketDao() {
        return new TicketMySqlDao(connection);
    }
    @Override
    public HallDao createHallDao() {
        return new HallMySqlDao(connection);
    }




    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DaoException("Cannot get connection", e);
        }
    }
}
