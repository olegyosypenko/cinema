package ua.training.model.dao.exceptions;

public class CannotGetConnectionException extends RuntimeException {
    public CannotGetConnectionException(Exception e) {
        super("Cannot get connection", e);
    }
}
