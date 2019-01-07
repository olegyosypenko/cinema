package ua.training.model.dao.exceptions;

public class NotUniqueValueException extends DaoException {

    public NotUniqueValueException(Exception e) {
        super("Not unique value", e);
    }
}
