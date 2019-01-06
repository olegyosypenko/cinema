package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.dao.Transaction;
import ua.training.model.dao.exceptions.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionMySql implements Transaction {
    private Logger logger = Logger.getLogger(TransactionMySql.class);
    private Connection connection;

    public TransactionMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("Cannot commit exception", e);
            throw new DaoException("Cannot commit exception", e);
        }
    }
    @Override
    public void startTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Cannot startTransaction exception", e);
            throw new DaoException("Cannot startTransaction exception", e);
        }
    }
    @Override
    public void rollback() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Cannot rollback exception", e);
            throw new DaoException("Cannot rollback exception", e);
        }
    }
    @Override
    public void setSerializable() {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException e) {
            logger.error("Cannot setSerializable exception", e);
            throw new DaoException("Cannot setSerializable exception", e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Cannot close exception", e);
            throw new DaoException("Cannot close exception", e);
        }
    }
}
