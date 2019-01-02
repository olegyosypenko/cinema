package ua.training.model.dao;

public interface Transaction extends AutoCloseable {
    void commit();

    void startTransaction();

    void rollback();

    void setSerializable();

    void close();
}
