package ua.training.model.dao.exceptions;

public class NotUniqueValueException extends RuntimeException {

    public NotUniqueValueException(Exception e) {
        super("Not unique value", e);
    }
}
