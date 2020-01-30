package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.dao.Transaction;
import ua.training.model.dao.exceptions.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionMySql implements Transaction {
    private Logger logger = Logger.getLogger(TransactionMySql.class);
    private Connection connection;

    TransactionMySql(Connection connection) {
        this.connection = connection;
        try {
            logger.debug("Isolation level: " + connection.getTransactionIsolation());
        } catch (SQLException e) {
            logger.error("Cannot get isolation level", e);
        }
    }


    /**
     * Commits all changes since the last commit.
     */
    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Cannot commit exception", e);
        }
    }

    /**
     * Sets autoCommit to false
     */
    @Override
    public void startTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Cannot startTransaction exception", e);
        }
    }
    /**
     * Undoes all the changes since last commit.
     */
    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Cannot rollback exception", e);
        }
    }

    /**
     * Sets isolation level to serializable.
     */
    @Override
    public void setSerializable() {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException e) {
            throw new DaoException("Cannot setSerializable exception", e);
        }
    }

    /**
     * Closes the connection.
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot close exception", e);
        }
    }
}
