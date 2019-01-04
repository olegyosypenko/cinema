package ua.training.model.dao.exceptions;

public class DaoException extends RuntimeException {
    public DaoException() {}
    public DaoException(String message) {
        super(message);
    }
    public DaoException(String message, Exception e) {
        super(message, e);
    }
}